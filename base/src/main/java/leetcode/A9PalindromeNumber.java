package leetcode;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A9PalindromeNumber {

    public static void main(String[] args) {
        A9PalindromeNumber palindromeNumber = new A9PalindromeNumber();
        System.out.println(palindromeNumber.isPalindrome2(121));
        System.out.println(palindromeNumber.isPalindrome2(12221));
        System.out.println(palindromeNumber.isPalindrome2(-121));
        System.out.println(palindromeNumber.isPalindrome2(10));
        System.out.println(palindromeNumber.isPalindrome2(-101));
        System.out.println(palindromeNumber.isPalindrome2(-2147483648));
        System.out.println(palindromeNumber.isPalindrome2(2147483647));
        System.out.println(palindromeNumber.isPalindrome2(2147447412));
        System.out.println(palindromeNumber.isPalindrome2(214747412));

    }

    /**
     * 1. 不足, 可以直接转成string, 确...
     * 2. 不足, < 0 的情况, 居然还在计算...
     * 3. 不足, 居然还转成了long...
     * 4. 看了题解, 回文可以只截取后半段对比
     * 5. 不足, 简单的题, 也可以有巧妙的解法呀...
     */
    public boolean isPalindrome1(int x) {
        long xx = (long) x;
        StringBuilder sb = new StringBuilder();
        if (xx < 0) {
            sb.append("-");
            xx = xx * -1;
        }
        while (xx > 0) {
            sb.append(xx % 10);
            xx /= 10;
        }
        int right = sb.length() -1;
        int left = 0;
        while (left <= right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public boolean isPalindrome2(int x){

        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;

    }
}
