package chip;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 编程实现7665的阶乘计算结果
 * 不能用{@link java.math.BigInteger} {@link java.math.BigDecimal}以及第三方的工具类
 * 运行结果要绝对正确
 */
public class FactorialTest {

    public static void main(String[] args) {
        int n = 7665;

        BigInteger result = BigInteger.ONE;
        long start = System.nanoTime();
        for (int i = 1; i <= n; i++) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }

        long end = System.nanoTime();

        System.out.println(result);
        System.out.printf("%40s %20s%n", "BigInteger 消耗时间", (end - start));
        //
        // MyInteger result2 = new MyInteger(1);
        // long start2 = System.nanoTime();
        // for (int i = 1; i <= n; i++) {
        //     result2 = result2.multiply(new MyInteger(i));
        // }
        // long end2 = System.nanoTime();
        // System.out.println(result2);
        // System.out.printf("%40s %20s%n", "MyInteger  消耗时间", (end2 - start2));
        // System.out.printf("是否相等 %s%n", result.toString().equals(result2.toString()));
        //
        // long start3 = System.nanoTime();
        // String result3 = factorial(n);
        // long end3 = System.nanoTime();
        // System.out.println(result3);
        // System.out.printf("%40s %20s%n", "ShareArr  消耗时间", (end3 - start3));
        // System.out.printf("是否相等 %s%n", result.toString().equals(result3));


        // MyInteger a = new MyInteger(4);
        // MyInteger b = new MyInteger(6);
        // System.out.println(a.multiply(b));

        // for (int i = 7665; i <= 7670; i++) {
        //
        //     MyInteger result4 = new MyInteger(1);
        //     long start4 = System.nanoTime();
        //     for (int j = 1; j <= i; j++) {
        //         result4 = result4.multiply(new MyInteger(j));
        //     }
        //     long end4 = System.nanoTime();
        //     long start44 = System.nanoTime();
        //     String result44 = factorial(i);
        //     long end44 = System.nanoTime();
        //     System.out.printf("i = %s, time = %20s, MyInteger   %s %n", i, end4 - start4,
        //             result4.toString());
        //     System.out.printf("i = %s, time = %20s, ShareArr    %s %n", i,
        //             end44 - start44, result44);
        //     newIntArrayTime.set(0);
        //     mulTime.set(0);
        //     consTime.set(0);
        //     MyInteger.newIntArrayTime.set(0);
        //     MyInteger.mulTime.set(0);
        //     MyInteger.consTime.set(0);
        //     resetTime.set(0);
        //     calValidLengthTime.set(0);
        //
        // }


        // for (int i = 0; i <= 3000; i++) {
        //     System.out.printf("i = %s, factorial is %s%n", i, factorial(i));
        // }

        // System.out.println(factorial(12));


    }

    private static final AtomicLong newIntArrayTime = new AtomicLong(0);

    private static final AtomicLong mulTime = new AtomicLong(0);

    private static final AtomicLong consTime = new AtomicLong(0);
    private static final AtomicLong resetTime = new AtomicLong(0);
    private static final AtomicLong calValidLengthTime = new AtomicLong(0);


    public static String factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return "1";
        }

        int decDigitLength = calDecDigitLength(n);
        long start = System.nanoTime();

        // 存储每一步的n值
        int[] a = new int[decDigitLength];
        // b c 交替存储计算结果
        int storeLength = decDigitLength << 1;
        int[] b = new int[storeLength];
        int[] c = new int[storeLength];
        long end = System.nanoTime();
        newIntArrayTime.addAndGet(end - start);
        int[] temp;
        // 将b初始化为1
        b[0] = 1;
        int aValidLength;
        int bValidLength;
        int nextCLength;
        for (int i = 2; i <= n; i++) {
            start = System.nanoTime();
            aValidLength = convertAndGetValidLength(i, a);
            end = System.nanoTime();
            consTime.addAndGet(end - start);
            start = System.nanoTime();
            bValidLength = calDecDigitLength(b);
            end = System.nanoTime();
            calValidLengthTime.addAndGet(end - start);
            nextCLength = aValidLength + bValidLength;
            if (nextCLength >= c.length) {
                start = System.nanoTime();
                temp = b;
                storeLength = nextCLength << 1;
                c = new int[storeLength];
                b = new int[storeLength];
                System.arraycopy(temp, 0, b, 0, bValidLength);
                end = System.nanoTime();
                newIntArrayTime.addAndGet(end - start);
            }
            start = System.nanoTime();
            multiply(a, aValidLength, b, bValidLength, c);
            end = System.nanoTime();
            mulTime.addAndGet(end - start);
            start = System.nanoTime();
            reset(b, bValidLength);
            end = System.nanoTime();
            resetTime.addAndGet(end - start);
            temp = b;
            b = c;
            c = temp;
        }

        // return toString(b);
        return toString(b) + String.format(" 构造消耗时间: %20s, new array消耗时间: %20s, mul" +
                        "计算消耗时间: " +
                        "%20s, validLength time: %20s, reset time: %20s", consTime, newIntArrayTime,
                mulTime, calValidLengthTime, resetTime);
    }


    public static void multiply(int[] a, int aValidLength, int[] b, int bValidLength, int[] c) {
        for (int i = 0; i < aValidLength; i++) {
            for (int j = 0; j < bValidLength; j++) {
                c[i + j] += a[i] * b[j];
            }
        }
        int cLength = aValidLength + bValidLength;
        for (int i = 0; i < cLength; i++) {
            int cDigit = c[i];
            c[i] = cDigit & 15;
            c[i + 1] += cDigit >> 4;
        }
    }

    public static int convertAndGetValidLength(final int n, int[] data) {
        int v = n;
        int length = data.length;
        int validLength = 0;
        for (int i = 0; v > 0 && i < length; i++) {
            data[i] = v & 15;
            v = v >> 4;
            validLength++;
        }
        return validLength;
    }

    public static void reset(final int[] data, int len) {
        for (int i = 0; i < len; i++) {
            data[i] = 0;
        }
    }

    public static int calDecDigitLength(final int[] data) {
        int length = data.length;
        for (int i = length - 1; i >= 0; i--) {
            if (data[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    public static int calDecDigitLength(final int n) {
        int x = 16;
        int length = 1;
        while (n >= x) {
            length++;
            x *= 16;
        }
        return length;
    }

    public static String toString(int[] data) {
        int validLength = calDecDigitLength(data);
        List<Integer> mods = new ArrayList<>();
        int lastMod = 0;
        while (validLength > 0) {
            for (int i = validLength - 1; i >= 0; i--) {
                int theDigit = (lastMod << 4) + data[i];
                data[i] = theDigit / 10;
                lastMod = theDigit % 10;
            }
            mods.add(lastMod);
            validLength = calDecDigitLength(data);
            lastMod = 0;
        }

        StringBuilder strBuilder = new StringBuilder();
        int size = mods.size();
        for (int i = size - 1; i >= 0; i--) {
            strBuilder.append(mods.get(i));
        }
        return strBuilder.toString();
    }

}
