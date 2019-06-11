package localcache;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) {
        int count = 10000000;

        LocalCache<String, String> cache = new LocalCache<>();
        ScheduledExecutorService service1 = Executors.newScheduledThreadPool(100);
        Random random1 = new Random();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                service1.schedule(() -> cache.put(UUID.randomUUID().toString(), "testValue", random1.nextInt(30)),
                        0, TimeUnit.MILLISECONDS);
            }
        });


        Random random2 = new Random();
        ScheduledExecutorService service2 = Executors.newScheduledThreadPool(100);
        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < count; i++) {
                service2.schedule(() -> LocalCacheTimer.put(UUID.randomUUID().toString(), "testValue", random2.nextInt(30)),
                        0, TimeUnit.MILLISECONDS);
            }
        });


        thread1.start();
        thread2.start();

    }
}
