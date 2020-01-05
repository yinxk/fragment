package concurrent.thread;

public class deadlock {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");
                synchronized (resourceB) {
                    System.out.println("threadA get resourceB lock");
                    System.out.println("threadA release resourceA lock");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try get resourceB lock");
                    synchronized (resourceB) {
                        System.out.println("threadB get resourceB lock");
                        System.out.println("threadB release resourceA lock");

                        resourceA.wait();
                    }
                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }

}
