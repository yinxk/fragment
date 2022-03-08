package leetcode;

/**
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 */
public class A633SumOfSquareNumbers {

    public static void main(String[] args) {
        A633SumOfSquareNumbers obj = new A633SumOfSquareNumbers();
        System.out.println(obj.judgeSquareSum(5));
        System.out.println(obj.judgeSquareSum(3));
        System.out.println(obj.judgeSquareSum(4));
        System.out.println(obj.judgeSquareSum(0));
        System.out.println(obj.judgeSquareSum(1000000000));
    }

    /**
     * 第一个想法肯定是 穷举, 应该会超时
     * 超时
     */
    public boolean judgeSquareSum(int c) {
        if (c <= 2) {
            return true;
        }
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    /**
     * 第一个想法肯定是 穷举, 应该会超时
     * 超时
     */
    public boolean judgeSquareSum1(int c) {
        int start = 0;
        for (int i = start; i <= c; i++) {
            for (int j = i; j <= c; j++) {
                if (i * i + j * j == c) {
                    return true;
                }
            }
        }
        return false;
    }


}
