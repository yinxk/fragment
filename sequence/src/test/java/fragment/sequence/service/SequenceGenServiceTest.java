package fragment.sequence.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
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

    private final static Map<BigInteger, AtomicInteger> counts = new ConcurrentHashMap<>();

    @Test
    public void nextVal() {
        int threadNumber = 20;
        int runTimes = 100000;
        String sequenceName = "testSeq100";
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        for (int i = 0; i < runTimes; i++) {
            executorService.submit(() -> {
                BigInteger nextVal = sequenceGenService.nextVal(sequenceName);
                counts.putIfAbsent(nextVal, new AtomicInteger(0));
                counts.get(nextVal).incrementAndGet();
            });
        }
        executorService.shutdown();
        while (true) {
            if (!executorService.isTerminated()) {
                continue;
            }
            System.out.println("====================");
            long end = System.currentTimeMillis();
            BigDecimal duration = new BigDecimal(end).subtract(new BigDecimal(start));
            BigDecimal qts = new BigDecimal(runTimes)
                    .divide(duration
                            .divide(new BigDecimal("1000"), 10, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);
            BigInteger max = BigInteger.ZERO;
            Map<Integer, AtomicInteger> countToCounts = new HashMap<>();
            for (Map.Entry<BigInteger, AtomicInteger> entry : counts.entrySet()) {
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
            System.out.println("QPS:" + qts);
            System.out.println("maxBigInteger:" + max);
            Assert.assertEquals(1, countToCounts.size());
            Assert.assertEquals(runTimes, countToCounts.get(1).get());
            break;
        }
    }

}