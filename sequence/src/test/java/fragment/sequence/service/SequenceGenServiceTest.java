package fragment.sequence.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fragment.sequence.SequenceApplication;
import fragment.sequence.dao.SequenceDao;
import fragment.sequence.exception.SequenceException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = SequenceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SequenceGenServiceTest {

    @Autowired
    SequenceGenService sequenceGenService;
    @Autowired
    SequenceDao sequenceDao;
    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    private List<String> seqNameList = new ArrayList<>();

    @Before
    public void before() {
        seqNameList = sequenceDao.findAllSequenceName();
        seqNameList.removeIf(s -> s.equals("testSequence-1"));
    }

    @Test
    public void insertSql() {
        String s = "INSERT INTO seq_sequence(sequence_name, min_value, max_value, cycle_flag, last_number, segment_size, dynamic_size) VALUES ('%s', 1, 99999999999999999999999999999999, b'%s', 0, %s, %s);";
        int i = -1;
        int step = 1;
        while (true) {
            if (i < 0) {
                System.out.println(String.format(s, "testSequence" + i, 1, i, 1));
            } else {
                System.out.println(String.format(s, "testSequence" + i, 0, i, 1));
            }
            i += step;
            if (i >= 10) {
                int t = i / 10;
                while (t >= 10) {
                    t = t / 10;
                }
                if (t == 1) {
                    step = i;
                }
            }
            if (i > 50_0000) {
                break;
            }
        }
    }

    @Test
    @PerfTest(threads = 10, invocations = 60_0000)
    public void conTest() {
        sequenceGenService.nextVal(seqNameList.get(new Random().nextInt(seqNameList.size())));
    }


    @Test
    public void verifyNextVal() {
        int threadNumber = 40;
        Random random = new Random();
        int runTimes = random.nextInt(2000_0000) + 1000;
        verify(threadNumber, runTimes, new HashSet<>(seqNameList));
    }

    @Test
    public void verifyNextValOneSequence() {
        int threadNumber = 40;
        Random random = new Random();
        int runTimes = random.nextInt(1000_0000) + 1000;
        int randomIndex = random.nextInt(seqNameList.size());
        Set<String> sequenceNameSet = new HashSet<>();
        sequenceNameSet.add(seqNameList.get(randomIndex));
        verify(threadNumber, runTimes, sequenceNameSet);
    }

    @Test
    public void verifyCycle() {
        int threadNumber = 40;
        Random random = new Random();
        int runTimes = random.nextInt(1000_0000) + 1000;
        Set<String> sequenceNameSet = new HashSet<>();
        sequenceNameSet.add("testSequence-1");
        verify(threadNumber, runTimes, sequenceNameSet, true);
    }

    private void verify(int threadNumber, int allRunTimes, Set<String> sequenceNameSet) {
        verify(threadNumber, allRunTimes, sequenceNameSet, false);
    }

    private void verify(int threadNumber, int allRunTimes, Set<String> sequenceNameSet, boolean cycle) {
        Random random = new Random();
        String[] sequenceNames = new String[sequenceNameSet.size()];
        sequenceNameSet.toArray(sequenceNames);

        final Map<String, Map<BigInteger, AtomicInteger>> countsMap = new ConcurrentHashMap<>();
        final Map<String, Integer> runTimesMap = new HashMap<>();
        final List<Exception> exceptionList = Collections.synchronizedList(new ArrayList<>());
        final CountDownLatch countDownLatch = new CountDownLatch(allRunTimes);
        for (String sequenceName : sequenceNames) {
            runTimesMap.put(sequenceName, 0);
            countsMap.put(sequenceName, new ConcurrentHashMap<>());
        }


        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        int sequenceNameSize = sequenceNames.length;
        long start = System.currentTimeMillis();
        for (int i = 0; i < allRunTimes; i++) {
            final String sequenceName = sequenceNames[random.nextInt(sequenceNameSize)];
            runTimesMap.put(sequenceName, runTimesMap.get(sequenceName) + 1);
            executorService.submit(() -> {
                try {
                    BigInteger nextVal = sequenceGenService.nextVal(sequenceName);
                    countsMap.get(sequenceName).putIfAbsent(nextVal, new AtomicInteger(0));
                    countsMap.get(sequenceName).get(nextVal).incrementAndGet();
                } catch (SequenceException e) {
                    exceptionList.add(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("==========结束后==========");

        BigDecimal duration = new BigDecimal(end).subtract(new BigDecimal(start));
        BigDecimal qts = new BigDecimal(allRunTimes)
                .divide(duration
                        .divide(new BigDecimal("1000"), 10, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);

        int realAllRunTimes = 0;
        List<String> seqNames = new ArrayList<>(countsMap.keySet());
        seqNames.sort(Comparator.comparingInt(o -> Integer.parseInt(o.substring(12))));
        for (String sequenceName : seqNames) {
            System.out.println("=========序列名: " + sequenceName + "  信息开始=========");
            BigInteger max = BigInteger.ZERO;
            Map<Integer, AtomicInteger> countToCounts = new HashMap<>();
            for (Map.Entry<BigInteger, AtomicInteger> entry : countsMap.get(sequenceName).entrySet()) {
                final int valueCount = entry.getValue().get();
                countToCounts.putIfAbsent(valueCount, new AtomicInteger(0));
                countToCounts.get(valueCount).getAndIncrement();
                if (entry.getKey().compareTo(max) > 0) {
                    max = entry.getKey();
                }
            }
            int num = 0;
            for (Map.Entry<Integer, AtomicInteger> countToCount : countToCounts.entrySet()) {
                System.out.println(countToCount);
                num += countToCount.getKey() * countToCount.getValue().get();
            }
            System.out.println("countToCount num:" + num);
            System.out.println("maxBigInteger:" + max);
            System.out.println("=========序列名: " + sequenceName + "  信息结束=========");
            realAllRunTimes += num;
            if (cycle) {
                Assert.assertTrue(1 <= countToCounts.size() && countToCounts.size() <= 2);
            } else {
                Assert.assertEquals(1, countToCounts.size());
                // Assert.assertEquals(runTimesMap.get(sequenceName).intValue(), countToCounts.get(1).get());
            }
        }

        System.out.println("==========");
        System.out.println("QPS:" + qts);
        System.out.println("allRunTimes:" + allRunTimes);
        System.out.println("realAllRunTimes:" + realAllRunTimes);
        System.out.println("exceptionTimes:" + exceptionList.size());
        System.out.println("==========");
        for (Exception exception : exceptionList) {
            System.out.println(exception);
        }
        Assert.assertEquals(allRunTimes, realAllRunTimes + exceptionList.size());

    }

}