package fragment.read.pattern.singleton;

public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {

    }

    /**
     * 为了解决线程不安全问题, 加锁, 但是性能差
     * <p>只有第一次实例化的时候, 才需要加锁, 其他时候不需要</p>
     * @return 单例
     */
    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
