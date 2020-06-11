package fragment.framework.security.async;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fragment.framework.security.FrameworkSecurityApplication;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkSecurityApplication.class)
@Slf4j
public class AsyncTestTest {

    @Autowired
    private AsyncTest asyncTest;

    @Autowired
    private Executor executor;

    @Test
    public void async() {

        int i = 0;
        int runNum = 10_000;
        CountDownLatch count = new CountDownLatch(runNum);

        long start = System.currentTimeMillis();
        int callerRunsNum = 0;
        while (++i <= runNum) {
            long eachStart = System.currentTimeMillis();
            asyncTest.async(count);
            long eachEnd = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (eachEnd - eachStart > 10) {
                log.info("testAsync, do: {}, time: {}", i, (eachEnd - eachStart));
                callerRunsNum++;
            }
        }

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        BigDecimal averageTps = new BigDecimal(runNum)
                .multiply(new BigDecimal(1000))
                .divide(new BigDecimal(time), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("time: " + time);
        System.out.println("runNum: " + runNum);
        System.out.println("averageTps: " + averageTps);
        System.out.println("callerRunsNum: " + callerRunsNum);
    }
}