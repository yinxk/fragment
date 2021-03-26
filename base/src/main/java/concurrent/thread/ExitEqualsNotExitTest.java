package concurrent.thread;

/**
 * -server 模式
 */
public class ExitEqualsNotExitTest {

    volatile boolean isExit;

    public void tryExit() {
        if (isExit == !isExit) {
            System.out.println("退出");
            System.exit(0);
        }
    }

    public void swapValue() {
        isExit = !isExit;
    }

    public static void main(String[] args) {

        ExitEqualsNotExitTest obj = new ExitEqualsNotExitTest();
        Thread mainThread = new Thread(() -> {
            System.out.println("main thread start");
            while (true) {
                obj.tryExit();
            }
        });
        mainThread.start();
        Thread swapThread = new Thread(() -> {
            System.out.println("swap thread start");
            while (true) {
                obj.swapValue();
            }
        });
        swapThread.start();

    }




}
