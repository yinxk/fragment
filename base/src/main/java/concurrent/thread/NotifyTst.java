package concurrent.thread;

public class NotifyTst {
    private static volatile Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (resource) {
                System.out.println("threadA get resource lock");
                try {
                    System.out.println("threadA begin wait");
                    resource.wait();
                    System.out.println("threadA end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (resource) {
                System.out.println("threadB get resource lock");
                try {
                    System.out.println("threadB begin wait");
                    resource.wait();
                    System.out.println("threadB end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadC = new Thread(() -> {
            synchronized (resource) {
                System.out.println("threadC begin notify");
                resource.notify();
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");

    }
}
