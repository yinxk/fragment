package threadlocal.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadLocalTest {

    static class MyData {
        private Byte[] data = new Byte[1024 * 1024 * 50];
    }

    static ThreadLocal<MyData> threadLocal = new ThreadLocal<>();

    static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(6);
    static Map<String, Integer> countMap = new HashMap<>();

    static String KEY = "countKey";

    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(20000);

        for (int i = 0; i < 20 ; i++) {

            executorService.execute(() -> {
                // 简单记下数, 间隔时间长, 多线程问题不考虑
                Integer count = countMap.getOrDefault(KEY, 0);
                count = count + 1;
                countMap.put(KEY, count);
                System.out.println(count + " " + Thread.currentThread().getName() + " set MyData");
                threadLocal.set(new MyData());
                // threadLocal.remove();
            });
            Thread.sleep(2000);
            if (i % 3 == 0) {
                threadLocal = new ThreadLocal<>();
                System.out.println("new ThreadLocal");
                System.gc();
                System.out.println("请求执行垃圾收集");
            }

        }
        Thread.sleep(10000);

        executorService.shutdown();
        System.out.println("结束");
    }
}
