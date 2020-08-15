package chip;

import java.math.BigInteger;

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
        System.out.printf("BigInteger 消耗时间 %s%n", (end - start));

        MyInteger result2 = new MyInteger(1);
        long start2 = System.nanoTime();
        for (int i = 1; i <= n; i++) {
            result2 = result2.multiply(new MyInteger(i));
        }
        long end2 = System.nanoTime();
        System.out.println(result2);
        System.out.printf("MyInteger 消耗时间 %s%n", (end2 - start2));
        System.out.printf("是否相等 %s%n", result.toString().equals(result2.toString()));
        // MyInteger a = new MyInteger(12345);
        // MyInteger b = new MyInteger(11111);
        // System.out.println(a.multiply(b));


    }

}
