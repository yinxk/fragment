package fragment.read.pattern.singleton;

public class Singleton4 {
    private volatile static Singleton4 instance;

    private Singleton4() {

    }

    /**
     * 使用双重检查锁
     * @return 单例
     */
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
