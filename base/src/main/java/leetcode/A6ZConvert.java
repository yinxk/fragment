package leetcode;

/**
 * https://leetcode-cn.com/problems/zigzag-conversion/
 */
public class A6ZConvert {

    public static void main(String[] args) {

        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {

        if (s == null || (s = s.trim()).length() == 0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        char[][] res = new char[numRows][len];

        int i = 0, j = 0;
        boolean topToBottom = true;
        return null;
    }


}
