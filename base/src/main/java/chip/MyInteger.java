package chip;

import java.util.concurrent.atomic.AtomicLong;

public class MyInteger {


    final int[] data;

    public static final AtomicLong newIntArrayTime = new AtomicLong(0);

    public static final AtomicLong mulTime = new AtomicLong(0);

    public static final AtomicLong consTime = new AtomicLong(0);


    public MyInteger(int value) {
        long start = System.nanoTime();
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
        int x = 10;
        int length = 1;
        while (value >= x) {
            length++;
            x *= 10;
        }
        data = new int[length];

        for (int i = 0; i < length; i++) {
            data[i] = (value % 10);
            value = value / 10;
        }
        consTime.addAndGet(System.nanoTime() - start);
    }

    private MyInteger(int[] data) {
        this.data = data;
    }

    public MyInteger multiply(MyInteger that) {
        int[] a = this.data;
        int[] b = that.data;
        int lenA = a.length;
        int lenB = b.length;
        int lenRes = lenA + lenB;
        long start = System.nanoTime();
        int[] res = new int[lenRes];
        MyInteger resInt = new MyInteger(res);
        long end = System.nanoTime();
        newIntArrayTime.addAndGet(end - start);

        start = System.nanoTime();
        for (int i = 0; i < lenB; i++) {
            for (int j = 0; j < lenA; j++) {
                int cIndex = i + j;
                int resBit = res[cIndex] + a[j] * b[i];
                res[cIndex] = resBit % 10;
                res[cIndex + 1] += resBit / 10;
            }
        }
        end = System.nanoTime();
        mulTime.addAndGet(end - start);
        return resInt;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        int length = data.length;
        boolean firstNotZero = false;
        for (int i = length - 1; i >= 0; i--) {
            if (!firstNotZero && data[i] != 0) {
                firstNotZero = true;
            }
            if (firstNotZero) {
                strBuilder.append(data[i]);
            }
        }
        return /*strBuilder.toString() +*/ String.format("构造消耗时间: %20s, new array消耗时间: %20s, mul" +
                "计算消耗时间: " +
                "%20s", consTime, newIntArrayTime, mulTime);
    }
}
