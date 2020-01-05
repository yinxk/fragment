package concurrent;

public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println(Thread.currentThread().getName() + "-未就绪");
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "得到值:" + number);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 2; i++) {
            new ReaderThread().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-设置值");
        number = 42;
        ready = true;
    }
}
