package leetcode;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class A7ReverseInteger {

    public static void main(String[] args) {

        System.out.println(reverse1(123));
        System.out.println(reverse1(-123));
        System.out.println(reverse1(120));
        System.out.println(reverse1(0));
        System.out.println(reverse1(901000));
        System.out.println(reverse1(2123456789));

    }

    // 能够达到目标, 但是借助了系统API的溢出检测
    public static int reverse1(int x) {
        String s = x + "";
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            sb.append('-');
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            if (c != '-') {
                sb.append(c);
            }
        }
        String revStr = sb.toString();
        try {
            return Integer.parseInt(revStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
