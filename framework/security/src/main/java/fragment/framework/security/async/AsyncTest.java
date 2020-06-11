package fragment.framework.security.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Async(Constants.ASYNC_EXECUTOR_NAME)
    public void async(CountDownLatch count) {
        try {
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            count.countDown();
        }
    }

}
