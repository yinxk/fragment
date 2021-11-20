package chip.date20211120.third;

import java.util.concurrent.TimeUnit;

/**
 * 模拟分布式锁
 */
public class DistributedLock {

    public boolean lock(String key, int timeout, TimeUnit timeUnit) {
        return true;
    }

    public void unlock(String key) {

    }
}
