package leetcode;

/**
 * https://leetcode-cn.com/problems/divide-two-integers/
 */
public class A29DivideTwoIntegers {


    public static void main(String[] args) {

        A29DivideTwoIntegers a29DivideTwoIntegers = new A29DivideTwoIntegers();
        System.out.println(a29DivideTwoIntegers.divide(10, 3));
        System.out.println(a29DivideTwoIntegers.divide(7, -3));
        System.out.println(a29DivideTwoIntegers.divide(-2147483648, -1));
    }


    /**
     * https://leetcode-cn.com/problems/divide-two-integers/solution/po-su-de-xiang-fa-mei-you-wei-yun-suan-mei-you-yi-/
     * 越界问题只要对除数是1和-1单独讨论就完事了啊
     * 关于如何提高效率快速逼近结果
     * 举个例子：11 除以 3 。
     * 首先11比3大，结果至少是1， 然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了，那我让这个6再翻倍，得12，11不比12大，吓死我了，差点让就让刚才的最小解2也翻倍得到4了。但是我知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个数是多少呢？我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法，看，递归出现了。说得很乱，不严谨，大家看个大概，然后自己在纸上画一画，或者直接看我代码就好啦！
     * <p>
     * 这里变化点在:
     * 使用负数进行数据存储
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            // 只要不是最小的那个整数，都是直接返回相反数就好啦
            if (dividend > Integer.MIN_VALUE) return -dividend;
            // 是最小的那个，那就返回最大的整数啦
            return Integer.MAX_VALUE;
        }
        int a = dividend;
        int b = divisor;
        int sign = 1;
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sign = -1;
        }
        // 变化点, 使用负数来进行数据存储
        a = a <= 0 ? a : -a;
        b = b <= 0 ? b : -b;
        int res = div(a, b);
        if (sign > 0) return res;
        return -res;
    }

    private int div(int a, int b) {
        // 似乎精髓和难点就在于下面这几句
        if (a > b) return 0;
        int count = 1;
        // 在后面的代码中不更新b
        int tb = b;
        while ((tb + tb) >= a && (tb + tb) < 0) {
            // 最小解翻倍
            count = count + count;
            // 当前测试的值也翻倍
            tb = tb + tb;
        }
        return count + div(a - tb, b);
    }


    /**
     * 超时 并且 溢出问题, 效率低
     */
    public int divide1(int dividend, int divisor) {
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
