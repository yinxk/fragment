package concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static class MyThread extends  Thread{
        @Override
        public void run() {
            System.out.println(this.getClass());
        }
    }
    public static class RunnableTask implements Runnable{
        @Override
        public void run() {
            System.out.println(this.getClass());
        }
    }

    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return this.getClass().getName();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        new Thread(new RunnableTask()).start();
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
