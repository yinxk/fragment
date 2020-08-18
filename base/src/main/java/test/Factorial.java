package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factorial {

    public static void main(String[] args) {

        System.out.println(factorial(7665, Radix.DEC));

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
     * @throws IllegalArgumentException 当 n < 0时 or n > 16384
     */
    public static String factorial(int n, Radix radix) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n > N_MAX) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return "1";
        }

        // 存储每一步的n值
        final int aValidLength = 1;
        long a;
        // b c 交替存储计算结果
        int storeLength = aValidLength << 1;
        long[] b = new long[storeLength];
        long[] c = new long[storeLength];
        long[] temp;
        // 将b初始化为1
        b[0] = 1;
        int bValidLength = 1;
        int nextCLength;
        for (int i = 2; i <= n; i++) {
            a = i;
            bValidLength = calValidDigitLength(b, bValidLength + 1);
            nextCLength = aValidLength + bValidLength;
            // 保证至少多一位
            if (nextCLength >= c.length) {
                temp = b;
                storeLength = nextCLength << 1;
                c = new long[storeLength];
                b = new long[storeLength];
                System.arraycopy(temp, 0, b, 0, bValidLength);
            }
            multiply(a, b, bValidLength, c);
            reset(b, bValidLength);
            temp = b;
            b = c;
            c = temp;
        }

        return Radix.DEC == radix ? toDecString(b) : toHexString(b);
    }


    private static void multiply(long a, long[] b, final int bValidLength,
                                long[] c) {
        for (int j = 0; j < bValidLength; j++) {
            long cDigit = c[j] + a * b[j];
            c[j] = cDigit & MASK;
            c[j + 1] += cDigit >> SHIFT;
        }
    }

    private static void reset(final long[] data, int len) {
        for (int i = 0; i < len; i++) {
            data[i] = 0;
        }
    }

    private static int calValidDigitLength(final long[] data, final int start) {
        int tStart = Math.min(data.length - 1, start - 1);
        for (int i = tStart; i >= 0; i--) {
            if (data[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * 转换为十六进制的字符串, 比{@link chip.Factorial#toDecString(long[])} 效率高
     *
     * @param data 大数数组
     * @return 十六进制字符串
     */
    private static String toHexString(long[] data) {
        int validLength = calValidDigitLength(data, data.length - 1);
        List<Long> mods = new ArrayList<>();
        long lastMod = 0;
        while (validLength > 0) {
            for (int i = validLength - 1; i >= 0; i--) {
                long theDigit = (lastMod << SHIFT) + data[i];
                // 这里和dec方式不一样, 为了不增加判断条件, 选择冗余
                data[i] = theDigit >> 4;
                lastMod = theDigit & 15;
            }
            mods.add(lastMod);
            validLength = calValidDigitLength(data, validLength);
            lastMod = 0;
        }

        StringBuilder strBuilder = new StringBuilder();
        int size = mods.size();
        for (int i = size - 1; i >= 0; i--) {
            Long digitNumber = mods.get(i);
            String digitChar = HEX_CHARS.get(digitNumber);
            strBuilder.append(digitChar == null ? digitNumber : digitChar);
        }
        return strBuilder.toString();
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
                // 这里和hex(2^n)方式不一样, 为了不增加判断条件, 选择冗余
                // 效率较低
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


    private static final int N_MAX_SHIFT = 14;
    private static final int SHIFT = 63 - N_MAX_SHIFT;
    private static final long N_MAX = 1L << N_MAX_SHIFT;
    private static final long SCALE = 1L << SHIFT;

    private static final long MASK = SCALE - 1L;

    private static final Map<Long, String> HEX_CHARS = new HashMap<>();

    static {
        HEX_CHARS.put(10L, "A");
        HEX_CHARS.put(11L, "B");
        HEX_CHARS.put(12L, "C");
        HEX_CHARS.put(13L, "D");
        HEX_CHARS.put(14L, "E");
        HEX_CHARS.put(15L, "F");
    }

}
