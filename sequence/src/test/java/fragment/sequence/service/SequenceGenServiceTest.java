package fragment.sequence.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fragment.sequence.SequenceApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = SequenceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SequenceGenServiceTest {

    @Autowired
    SequenceGenService sequenceGenService;
    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    private final static Map<String, Integer> ALL_SEQ = new HashMap<>();

    static {
        ALL_SEQ.put("testSeq10", 10000);
        ALL_SEQ.put("testSeq20", 20000);
        ALL_SEQ.put("testSeq30", 30000);
        ALL_SEQ.put("testSeq40", 40000);
        ALL_SEQ.put("testSeq50", 50000);
        ALL_SEQ.put("testSeq100", 1000_0000);
        ALL_SEQ.put("testSeq200", 20_0000);
        ALL_SEQ.put("testSeq300", 30_0000);
        ALL_SEQ.put("testSeq400", 40_0000);
        ALL_SEQ.put("testSeq500", 50_0000);
        ALL_SEQ.put("testSeq600", 60_0000);
        ALL_SEQ.put("testSeq700", 70_0000);
        ALL_SEQ.put("testSeq800", 80_0000);
        ALL_SEQ.put("testSeq900", 90_0000);
        ALL_SEQ.put("testSeq1000", 100_0000);
        ALL_SEQ.put("testSeq2000", 100_0000);
        ALL_SEQ.put("testSeq3000", 100_0000);
        ALL_SEQ.put("testSeq5000", 100_0000);
        ALL_SEQ.put("testSeq9999", 200_0000);
    }


    @Test
    @PerfTest(threads = 10, invocations = 100_0000)
    public void conTest() {
        sequenceGenService.nextVal("testSeq100");
    }


    @Test
    public void verifyNextVal() {
        int threadNumber = 10;
        int runTimes = 60_0000;
        verify(threadNumber, runTimes, ALL_SEQ.keySet());
    }

    @Test
    public void verifyNextValOneSequence() {
        int threadNumber = 10;
        Random random = new Random();
        int size = ALL_SEQ.size();
        String[] names = new String[size];
        ALL_SEQ.keySet().toArray(names);
        int randomIndex = random.nextInt(names.length);
        String seqName = "testSeq100";
        int runTimes = ALL_SEQ.get(seqName);
        Set<String> sequenceNameSet = new HashSet<>();
        sequenceNameSet.add(seqName);
        verify(threadNumber, runTimes, sequenceNameSet);
    }

    private void verify(int threadNumber, int allRunTimes, Set<String> sequenceNameSet) {
        Random random = new Random();
        String[] sequenceNames = new String[sequenceNameSet.size()];
        sequenceNameSet.toArray(sequenceNames);

        final Map<String, Map<BigInteger, AtomicInteger>> countsMap = new ConcurrentHashMap<>();
        final Map<String, Integer> runTimesMap = new HashMap<>();
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
                BigInteger nextVal = sequenceGenService.nextVal(sequenceName);
                countsMap.get(sequenceName).putIfAbsent(nextVal, new AtomicInteger(0));
                countsMap.get(sequenceName).get(nextVal).incrementAndGet();
            });
        }
        executorService.shutdown();
        while (true) {
            if (!executorService.isTerminated()) {
                continue;
            }
            long end = System.currentTimeMillis();
            System.out.println("==========结束后==========");

            BigDecimal duration = new BigDecimal(end).subtract(new BigDecimal(start));
            BigDecimal qts = new BigDecimal(allRunTimes)
                    .divide(duration
                            .divide(new BigDecimal("1000"), 10, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);

            int realAllRunTimes = 0;
            for (Map.Entry<String, Map<BigInteger, AtomicInteger>> counts : countsMap.entrySet()) {
                String sequenceName = counts.getKey();
                System.out.println("=========序列名: " + sequenceName + "  信息开始=========");
                BigInteger max = BigInteger.ZERO;
                Map<Integer, AtomicInteger> countToCounts = new HashMap<>();
                for (Map.Entry<BigInteger, AtomicInteger> entry : counts.getValue().entrySet()) {
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
                Assert.assertEquals(1, countToCounts.size());
                Assert.assertEquals(runTimesMap.get(sequenceName).intValue(), countToCounts.get(1).get());
            }

            System.out.println("==========");
            System.out.println("QPS:" + qts);
            System.out.println("realAllRunTimes:" + realAllRunTimes);
            System.out.println("==========");
            Assert.assertEquals(allRunTimes, realAllRunTimes);

            break;
        }
    }

}