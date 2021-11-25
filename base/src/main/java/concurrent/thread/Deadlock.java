package concurrent.thread;

import cn.hutool.core.util.StrUtil;

public class Deadlock {

    private static final String RESOURCE_A = "资源A";
    private static final String RESOURCE_B = "资源B";

    public static void main(String[] args) throws InterruptedException {

        new Thread(new HoldLockThread(RESOURCE_A, RESOURCE_B), "thread-a").start();
        new Thread(new HoldLockThread(RESOURCE_B, RESOURCE_A), "thread-b").start();
        System.out.println("main over");
    }


    public static class HoldLockThread implements Runnable {
        private final Object resourceA;
        private final Object resourceB;

        public HoldLockThread(Object resourceA, Object resourceB) {
            this.resourceA = resourceA;
            this.resourceB = resourceB;
        }

        @Override
        public void run() {
            synchronized (resourceA) {
                System.out.println(StrUtil.format("[{}] 锁定资源[{}]后, 尝试对资源[{}]加锁",
                        Thread.currentThread().getName(),
                        resourceA, resourceB));
                synchronized (resourceB) {
                    System.out.println(StrUtil.format("[{}] 锁定资源[{}]后, 再次锁定资源[{}]",
                            Thread.currentThread().getName(),
                            resourceA, resourceB));
                }
            }
        }
    }
}
