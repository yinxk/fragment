package threadlocal.test1;

public class TestUtil {
    private static final ThreadLocal<Boolean> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(){
        THREAD_LOCAL.set(Boolean.TRUE);
        System.out.println("now: " + System.currentTimeMillis() + " threadName: " + Thread.currentThread().getName() + " set ----");
    }

    public static void get() {
        System.out.println("now: " + System.currentTimeMillis() + " threadName: " + Thread.currentThread().getName() + " getValue: " + THREAD_LOCAL.get());
    }
}
