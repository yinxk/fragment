package threadlocal.test1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(()->{
            TestUtil.setCheck(Boolean.TRUE);
            TestUtil.check("testStr");
        });
    }
}
