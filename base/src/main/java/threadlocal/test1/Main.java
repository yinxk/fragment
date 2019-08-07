package threadlocal.test1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.schedule(TestUtil::set, 0, TimeUnit.SECONDS);
        }
        executorService.scheduleAtFixedRate(TestUtil::get, 3,1, TimeUnit.SECONDS);

        while (true) {
            System.gc();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
