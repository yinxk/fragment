package chip;

/**
 * 编程实现7665的阶乘计算结果
 * 不能用{@link java.math.BigInteger} {@link java.math.BigDecimal}以及第三方的工具类
 * 运行结果要绝对正确
 * <p>
 * 该题没有明确指出返回的表示格式, 那么其实上16进制的结果也是合理的, 直接使用16进制做为结果来解答
 */
public class Factorial {

    public static void main(String[] args) {
        long start = System.nanoTime();
        String result = factorial(7665);
        long end = System.nanoTime();
        System.out.printf("消耗时间: %s ns, %n结果为: %s %n", (end - start), result);

        // long allTime = 0L;
        // int allCount = 0;
        // for (int n = 7500; n <= 10000; n++) {
        //     allCount++;
        //     BigInteger resultBit = BigInteger.ONE;
        //     long start = System.nanoTime();
        //     for (int i = 1; i <= n; i++) {
        //         resultBit = resultBit.multiply(new BigInteger(String.valueOf(i)));
        //     }
        //     String result = resultBit.toString(16);
        //     long end = System.nanoTime();
        //
        //     long start3 = System.nanoTime();
        //     String result3 = factorial(n);
        //     long end3 = System.nanoTime();
        //     long thisTime = end3 - start3;
        //     // System.out.printf("%s %20s, n = %s %n", "MyFactorial 消耗时间", thisTime, n);
        //     allTime += thisTime;
        //     System.out.printf("%s %20s, %s %20s, n = %s, 倍数关系: %s %n", "BigInteger 消耗时间",
        //             (end - start),
        //             "MyFactorial 消耗时间", (end3 - start3), n, (double) (end - start) / (end3 - start3));
        //     // System.out.printf("值1: %s, 值2: %s \n", result, result3);
        //     if (!result.equalsIgnoreCase(result3)) {
        //         System.err.printf("不相等 %n");
        //     }
        // }
        // if (allCount > 0) {
        //     System.out.printf("消耗时间和: %s, 平均时间: %s %n", allTime, allTime / allCount);
        // }

    }


    /**
     * 阶乘
     *
     * @param n 数字n
     * @return 阶乘结果十六进制字符串
     * @throws IllegalArgumentException 当 n < 0时 or n > {@link Factorial#N_MAX}
     */
    public static String factorial(int n) {
        if (n < 0 || n > N_MAX) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return "1";
        }

        // 存储每一步的n值
        final int aValidLength = 1;
        long a;
        int storeLength = aValidLength << 1;
        long[] b = new long[storeLength];
        long[] temp;
        // 将b初始化为1
        b[0] = 1;
        int bValidLength = 1;
        int nextBLength;
        for (int i = 2; i <= n; i++) {
            a = i;
            bValidLength = calValidDigitLength(b, bValidLength + 1);
            nextBLength = aValidLength + bValidLength;
            // 保证至少多一位
            if (nextBLength >= b.length) {
                temp = b;
                storeLength = nextBLength << 1;
                b = new long[storeLength];
                System.arraycopy(temp, 0, b, 0, bValidLength);
            }
            multiply(a, b, bValidLength);
        }

        return toHexString(b);
    }


    private static void multiply(long a, long[] b, final int bValidLength) {
        long carry = 0L;
        for (int j = 0; j < bValidLength; j++) {
            long cDigit = carry + a * b[j];
            b[j] = cDigit & MASK;
            carry = cDigit >> SHIFT;
        }
        b[bValidLength] = carry;
    }

    private static int calValidDigitLength(final long[] data, final int startLength) {
        int tStart = Math.min(data.length - 1, startLength - 1);
        for (int i = tStart; i >= 0; i--) {
            if (data[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * 转换为十六进制的字符串
     *
     * @param data 大数数组
     * @return 十六进制字符串
     */
    private static String toHexString(long[] data) {
        int validLength = calValidDigitLength(data, data.length - 1);
        long digit;
        StringBuilder sb = new StringBuilder();
        for (int i = validLength - 1; i >= 0; i--) {
            digit = data[i];
            for (int j = SHIFT - 4; j >= 0; j -= 4) {
                appendHexDigit(sb, digit, j);
            }
        }
        while (sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    private static void appendHexDigit(StringBuilder sb, long digit, int shift) {
        byte digitByte = (byte) ((int) (digit >>> shift) & 15);
        if (digitByte < 10) {
            sb.append(digitByte);
        } else {
            sb.append((char) (digitByte + 55));
        }
    }

    private static final int N_MAX_SHIFT = 15;
    // 需要为4的倍数, 16进制方便转换
    private static final int SHIFT = 63 - N_MAX_SHIFT;
    private static final long N_MAX = 1L << N_MAX_SHIFT;
    private static final long SCALE = 1L << SHIFT;
    private static final long MASK = SCALE - 1L;

}
