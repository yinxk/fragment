package leetcode;

/**
 * https://leetcode-cn.com/problems/divide-two-integers/
 */
public class A29DivideTwoIntegers {


    public static void main(String[] args) {

        A29DivideTwoIntegers a29DivideTwoIntegers = new A29DivideTwoIntegers();
//        System.out.println(a29DivideTwoIntegers.divide(10, 3));
//        System.out.println(a29DivideTwoIntegers.divide(7, -3));
        System.out.println(a29DivideTwoIntegers.divide(-2147483648, -1));
    }


    /**
     * 超时 并且 溢出问题, 效率低
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("by /zero");
        }
        int signA = dividend >= 0 ? 1 : -1;
        int signB = divisor >= 0 ? 1 : -1;
        int resSign = signA + signB;

        int div = signA > 0 ? dividend : -dividend;
        int divs = signB > 0 ? divisor : -divisor;

        int count = 0;
        while (div - divs >= 0) {
            count++;
            div = div - divs;
        }
        if (resSign != 0 && count < 0) {
            count--;
        }
        return resSign == 0 ? -count : count;
    }


}
