package localcache;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {

        Random random = new Random();

        LocalCache<String, String> cache = new LocalCache<>();


        ScheduledExecutorService service = Executors.newScheduledThreadPool(100);

        for (int i = 0; i < 1000000; i++) {
            service.schedule(() -> cache.put(UUID.randomUUID().toString(), "testValue", random.nextInt(30)),
                    0, TimeUnit.MILLISECONDS);
        }
    }
}
