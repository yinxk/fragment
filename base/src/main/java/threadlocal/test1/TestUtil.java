package threadlocal.test1;

public class TestUtil {
    private static final ThreadLocal<Boolean> THREAD_LOCAL = ThreadLocal.withInitial(() -> Boolean.FALSE);


    public static void setCheck(Boolean b) {
        System.out.println("set: " + Thread.currentThread().getName());
        THREAD_LOCAL.set(b);
    }

    public static boolean check(String s) {
        System.out.println("check: " + Thread.currentThread().getName());
        if (THREAD_LOCAL.get()) {
            return "testStr3".equals(s);
        }
        return false;
    }
}
