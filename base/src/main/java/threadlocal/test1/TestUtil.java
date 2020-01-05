package threadlocal.test1;

import java.math.BigDecimal;

public class TestUtil {
    private static final ThreadLocal<BigDecimal> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(BigDecimal value){
        THREAD_LOCAL.set(value);
        System.out.println("now: " + System.currentTimeMillis() + " threadName: " + Thread.currentThread().getName() + " set ----");
    }

    public static void get() {
        System.out.println("now: " + System.currentTimeMillis() + " threadName: " + Thread.currentThread().getName() + " getValue: " + THREAD_LOCAL.get());
    }
}
