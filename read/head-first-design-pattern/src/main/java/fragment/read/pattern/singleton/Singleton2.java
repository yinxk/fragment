package fragment.read.pattern.singleton;

public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {

    }
    public static Singleton2 getInstance() {
        // 线程不安全
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
