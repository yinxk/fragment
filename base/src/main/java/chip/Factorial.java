package chip;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 编程实现7665的阶乘计算结果
 * 不能用{@link java.math.BigInteger} {@link java.math.BigDecimal}以及第三方的工具类
 * 运行结果要绝对正确
 */
public class Factorial {

    public static void main(String[] args) {

        long allTime = 0L;
        int allCount = 0;
        for (int n = 7500; n <= 8000; n++) {
            allCount++;
            BigInteger resultBit = BigInteger.ONE;
            long start = System.nanoTime();
            for (int i = 1; i <= n; i++) {
                resultBit = resultBit.multiply(new BigInteger(String.valueOf(i)));
            }
            String result = resultBit.toString();
            long end = System.nanoTime();

            long start3 = System.nanoTime();
            String result3 = factorial(n, Radix.DEC);
            long end3 = System.nanoTime();
            long thisTime = end3 - start3;
            // System.out.printf("%s %20s, n = %s %n", "ShareArr  消耗时间", thisTime, n);
            allTime += thisTime;
            System.out.printf("%s %20s, %s %20s, n = %s, 倍数关系: %s %n", "BigInteger 消耗时间",
                    (end - start),
                    "ShareArr  消耗时间", (end3 - start3), n, (end3 - start3) / (end - start));
            if (!result.equalsIgnoreCase(result3)) {
                System.err.printf("不相等 %n");
            }
        }
        if (allCount > 0) {
            System.out.printf("消耗时间和: %s, 平均时间: %s %n", allTime, allTime / allCount);
        }


        // System.out.println(factorial(7665));

    }

    public enum Radix {
        /**
         * 10
         */
        DEC,
        /**
         * 16
         */
        HEX
    }

    /**
     * 阶乘
     *
     * @param n     数字n
     * @param radix 结果以十进制或者十六进制表示
     * @return 阶乘结果十进制/十六进制字符串
     * @throws IllegalArgumentException 当 n < 0时 or n > {@link Factorial#N_MAX}
     */
    public static String factorial(int n, Radix radix) {
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

        return Radix.HEX == radix ? toHexString(b) : toDecString(b);
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
     * 转换为十六进制的字符串, 比{@link Factorial#toDecString(long[])} 效率高
     *
     * @param data 大数数组
     * @return 十六进制字符串
     */
    private static String toHexString(long[] data) {
        int validLength = calValidDigitLength(data, data.length - 1);
        long digit;
        byte[] hexDigits = new byte[SHIFT / 4];
        byte[] temp;
        int hexIndex = -1;
        for (int i = validLength - 1; i >= 0; i--) {
            digit = data[i];
            if (hexIndex >= hexDigits.length - 1) {
                temp = hexDigits;
                hexDigits = new byte[hexDigits.length << 1];
                System.arraycopy(temp, 0, hexDigits, 0, temp.length);
            }
            for (int j = SHIFT - 4; j >= 0; j -= 4) {
                toHexDigit(hexDigits, digit, ++hexIndex, j);
            }
        }
        int start = 0;
        int end = hexIndex + 1;
        for (int i = 0; i < end; i++) {
            if (hexDigits[i] != 0) {
                start = i;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            byte hexDigit = hexDigits[i];
            if (hexDigit < 10) {
                sb.append(hexDigit);
            } else {
                sb.append((char) (hexDigit + 55));
            }
        }
        return sb.toString();
    }

    private static void toHexDigit(byte[] hexDigits, long digit, int index, int shift) {
        hexDigits[index] = (byte) ((int) (digit >>> shift) & 15);
    }

    /**
     * 转换为十进制字符串, 效率较低
     *
     * @param data 大数数组
     * @return 十进制字符串
     */
    private static String toDecString(long[] data) {
        int validLength = calValidDigitLength(data, data.length - 1);
        List<Long> mods = new ArrayList<>();
        long lastMod = 0;
        while (validLength > 0) {
            for (int i = validLength - 1; i >= 0; i--) {
                long theDigit = (lastMod << SHIFT) + data[i];
                data[i] = theDigit / 10;
                lastMod = theDigit % 10;
            }
            mods.add(lastMod);
            validLength = calValidDigitLength(data, validLength);
            lastMod = 0;
        }

        StringBuilder strBuilder = new StringBuilder();
        int size = mods.size();
        for (int i = size - 1; i >= 0; i--) {
            strBuilder.append(mods.get(i));
        }
        return strBuilder.toString();
    }


    private static final int N_MAX_SHIFT = 15;
    // 必须为4的倍数
    private static final int SHIFT = 63 - N_MAX_SHIFT;
    private static final long N_MAX = 1L << N_MAX_SHIFT;
    private static final long SCALE = 1L << SHIFT;
    private static final long MASK = SCALE - 1L;

}
