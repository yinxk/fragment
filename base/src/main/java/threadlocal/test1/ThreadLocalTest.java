package threadlocal.test1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import cn.hutool.core.util.RandomUtil;

public class ThreadLocalTest {

    static class MyData {
        private final Byte[] data = new Byte[1024 * 1024 * 10];
    }

    static int threadNum = 10;
    static ExecutorService executorService = new ThreadPoolExecutor(threadNum, threadNum, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
    static AtomicLong count = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(20000);


        List<ThreadLocal<MyData>> threadLocalList = new ArrayList<>();
        // 多个threadlocal, threadlocalmap hash冲突
        for (int i = 0; i < 100; i++) {
            threadLocalList.add(new ThreadLocal<>());
        }
        for (final ThreadLocal<MyData> threadLocal : threadLocalList) {
            for (int i = 0; i < 100; i++) {
                executorService.execute(() -> {
                    System.out.println(count.incrementAndGet() + " " + Thread.currentThread().getName() + " set MyData");
                    threadLocal.set(new MyData());
                    // threadLocal.remove();
                });
                TimeUnit.MILLISECONDS.sleep(200);
                if (i % (RandomUtil.randomInt(threadNum) + 1) == 0) {
                    System.gc();
                }
            }
        }

        System.out.println("==================================");

        for (final ThreadLocal<MyData> threadLocal : threadLocalList) {
            executorService.execute(() -> {
                System.out.println(count.incrementAndGet() + " " + Thread.currentThread().getName() + " set MyData");
                threadLocal.set(new MyData());
                // threadLocal.remove();
            });
        }

        for (final ThreadLocal<MyData> threadLocal : threadLocalList) {
            for (int i = 0; i < threadNum * 2; i++) {
                executorService.execute(() -> {
                    MyData myData = threadLocal.get();
                    if (myData == null) {
                        System.out.println(Thread.currentThread().getName() + " get myData is null");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " get myData length is: " + Optional.ofNullable(myData.data).map(Array::getLength).orElse(-1));
                    }
                });
            }
        }

        TimeUnit.MINUTES.sleep(5);
        executorService.shutdown();
        System.out.println("结束");
    }
}
