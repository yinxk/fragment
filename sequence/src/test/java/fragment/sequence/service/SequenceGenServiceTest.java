package fragment.sequence.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fragment.sequence.SequenceApplication;
import fragment.sequence.dao.SequenceDao;
import fragment.sequence.exception.SequenceException;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

@SpringBootTest(classes = SequenceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
@Transactional
public class SequenceGenServiceTest {

    @Autowired
    SequenceGenService sequenceGenService;
    @Autowired
    SequenceDao sequenceDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
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
        int runTimes = random.nextInt(2000_0000) + 1000;
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

        final Map<String, Map<BigInteger, AtomicInteger>> name2Value2CountsMap = new ConcurrentHashMap<>();
        final Map<String, Integer> name2RunTimesMap = new HashMap<>();
        final List<Exception> allExceptionList = Collections.synchronizedList(new ArrayList<>());
        final CountDownLatch countDownLatch = new CountDownLatch(allRunTimes);
        for (String sequenceName : sequenceNames) {
            name2RunTimesMap.put(sequenceName, 0);
            name2Value2CountsMap.put(sequenceName, new ConcurrentHashMap<>());
        }


        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);

        int sequenceNameSize = sequenceNames.length;
        long start = System.currentTimeMillis();
        for (int i = 0; i < allRunTimes; i++) {
            final String sequenceName = sequenceNames[random.nextInt(sequenceNameSize)];
            name2RunTimesMap.put(sequenceName, name2RunTimesMap.get(sequenceName) + 1);
            executorService.submit(() -> {
                try {
                    BigInteger nextVal = sequenceGenService.nextVal(sequenceName);
                    name2Value2CountsMap.get(sequenceName).putIfAbsent(nextVal, new AtomicInteger(0));
                    name2Value2CountsMap.get(sequenceName).get(nextVal).incrementAndGet();
                } catch (SequenceException e) {
                    allExceptionList.add(e);
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
        System.out.println("==========END==========");


        BigDecimal duration = new BigDecimal(end).subtract(new BigDecimal(start));
        BigDecimal qts = new BigDecimal(allRunTimes)
                .divide(duration
                        .divide(new BigDecimal("1000"), 10, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);

        int successAllRunTimes = 0;
        Map<String, AtomicInteger> exceptionMap = new HashMap<>();
        for (Exception exception : allExceptionList) {
            exceptionMap.putIfAbsent(exception.getMessage(), new AtomicInteger(0));
            exceptionMap.get(exception.getMessage()).incrementAndGet();
        }
        List<String> seqNames = new ArrayList<>(name2Value2CountsMap.keySet());
        seqNames.sort(Comparator.comparingInt(o -> Integer.parseInt(o.substring(12))));
        for (String sequenceName : seqNames) {
            System.out.println(">>>>>>>>> sequence: " + sequenceName + "  info start >>>>>>>>>");
            BigInteger maxValue = BigInteger.ZERO;
            Map<Integer, AtomicInteger> valueCount2Counts = new HashMap<>();
            for (Map.Entry<BigInteger, AtomicInteger> entry : name2Value2CountsMap.get(sequenceName).entrySet()) {
                final int valueCount = entry.getValue().get();
                valueCount2Counts.putIfAbsent(valueCount, new AtomicInteger(0));
                valueCount2Counts.get(valueCount).getAndIncrement();
                if (entry.getKey().compareTo(maxValue) > 0) {
                    maxValue = entry.getKey();
                }
            }
            int successRunTimes = 0;
            for (Map.Entry<Integer, AtomicInteger> valueCount2Count : valueCount2Counts.entrySet()) {
                System.out.println("value occurrence number: " + valueCount2Count.getKey()
                        + ", and " + valueCount2Count.getKey() + " occurrence number:" + valueCount2Count.getValue());
                successRunTimes += valueCount2Count.getKey() * valueCount2Count.getValue().get();
            }
            AtomicInteger exceptionTimes =
                    Optional.ofNullable(exceptionMap.get(sequenceName)).orElse(new AtomicInteger(0));
            System.out.println("runTimes:" + name2RunTimesMap.get(sequenceName));
            System.out.println("successRunTimes:" + successRunTimes);
            System.out.println("exceptionRunTimes:" + exceptionTimes);
            System.out.println("maxValue:" + maxValue);
            System.out.println("<<<<<<<<< sequence: " + sequenceName + "  info end <<<<<<<<<");
            successAllRunTimes += successRunTimes;
            if (cycle) {
                Assert.assertTrue(1 <= valueCount2Counts.size() && valueCount2Counts.size() <= 2);
            } else {
                assertEquals(1, valueCount2Counts.size());
                Assert.assertEquals(name2RunTimesMap.get(sequenceName).intValue(),
                        valueCount2Counts.get(1).get() + exceptionTimes.get());
                // 一般运行次数不超过int范围
                assertEquals(valueCount2Counts.get(1).get(), maxValue.intValue());
            }
        }

        System.out.println("==========");
        System.out.println("QPS:" + qts);
        System.out.println("allRunTimes:" + allRunTimes);
        System.out.println("successAllRunTimes:" + successAllRunTimes);
        System.out.println("exceptionTimes:" + allExceptionList.size());
        System.out.println("==========");
        for (Exception exception : allExceptionList) {
            System.out.println(exception);
        }
        assertEquals(allRunTimes, successAllRunTimes + allExceptionList.size());

    }

}