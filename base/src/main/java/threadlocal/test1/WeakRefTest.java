package threadlocal.test1;


import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakRefTest {

    @Test
    public void test1() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o, referenceQueue);
        System.gc();
        System.out.println(weakReference.get() == null);
        System.out.println(referenceQueue.poll());
        System.out.println("====");

        o = null;
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakReference.get() == null);
        System.out.println(referenceQueue.poll());
        System.out.println("====");

    }
}
