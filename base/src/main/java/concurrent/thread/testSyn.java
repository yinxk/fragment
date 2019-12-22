package concurrent.thread;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class testSyn {


    public static class testModel2 {
        private AtomicLong value = new AtomicLong(0);
        private BigInteger valueBigInteger = BigInteger.ZERO;

        public AtomicLong getValue() {
            return value;
        }

        public void setValue(AtomicLong value) {
            this.value = value;
        }

        public BigInteger getValueBigInteger() {
            return valueBigInteger;
        }

        public void setValueBigInteger(BigInteger valueBigInteger) {
            this.valueBigInteger = valueBigInteger;
        }

        @Override
        public String toString() {
            return "testModel2{" +
                    "value=" + value +
                    ", valueBigInteger=" + valueBigInteger +
                    '}';
        }
    }

    public static class testModel {
        private AtomicLong value = new AtomicLong(0);
        private BigInteger valueBigInteger = BigInteger.ZERO;
        private ReadWriteLock lock = new ReentrantReadWriteLock();

        private testModel2 testModel2 = new testModel2();

        public testSyn.testModel2 getTestModel2() {
            return testModel2;
        }

        public void setTestModel2(testSyn.testModel2 testModel2) {
            this.testModel2 = testModel2;
        }

        public AtomicLong getValue() {
            return value;
        }

        public void setValue(AtomicLong value) {
            this.value = value;
        }

        public BigInteger getValueBigInteger() {
            return valueBigInteger;
        }

        public void setValueBigInteger(BigInteger valueBigInteger) {
            this.valueBigInteger = valueBigInteger;
        }

        public ReadWriteLock getLock() {
            return lock;
        }

        public void setLock(ReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public String toString() {
            return "testModel{" +
                    "value=" + value +
                    ", valueBigInteger=" + valueBigInteger +
                    ", testModel2=" + testModel2 +
                    '}';
        }
    }


    private final static Map<String, testModel> cache = new ConcurrentHashMap<>();

    static {
        cache.put("test1", new testModel());
        cache.put("test2", new testModel());
        cache.put("test3", new testModel());
        cache.put("test4", new testModel());
    }

    private final static ExecutorService service =
            new ThreadPoolExecutor(2, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());


    public void test1() {
        testModel test1 = cache.get("test1");
        execute(test1);

    }

    public void test2() {
        int size = cache.size();
        for (int i = 0; i < 3; i++) {
            for (int k = 1; k <= size; k++) {
                testModel test = cache.get("test" + k);
                execute(test);
            }
        }

    }

    private void execute(testModel test1) {
        for (int j = 0; j < 20; j++) {
            service.execute(() -> {
                for (int i = 0; i < 10000; i++) {
                    test1.getValue().incrementAndGet();
                    test1.getTestModel2().getValue().incrementAndGet();
                    synchronized (test1) {
                        try {
                            BigInteger modelAddValue = test1.getValueBigInteger().add(BigInteger.ONE);
                            BigInteger model2AddValue = test1.getTestModel2().getValueBigInteger().add(BigInteger.ONE);
                            test1.setValueBigInteger(modelAddValue);
                            test1.getTestModel2().setValueBigInteger(model2AddValue);
                            // System.out.printf("threadName: %s, model: %s \n",
                            //         Thread.currentThread().getName(), test1);
                        } catch (Exception e) {

                        }
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new testSyn().test2();
        service.shutdown();
        while (true) {
            if (service.isTerminated()) {
                for (Map.Entry<String, testModel> entry : cache.entrySet()) {
                    System.out.println(entry);
                }
                break;
            }
        }
    }
}
