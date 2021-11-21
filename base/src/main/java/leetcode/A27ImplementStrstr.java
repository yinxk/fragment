package leetcode;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class A27ImplementStrstr {

    public static void main(String[] args) {

        A27ImplementStrstr a27ImplementStrstr = new A27ImplementStrstr();
        System.out.println(a27ImplementStrstr.strStr("hello", "ll"));
        System.out.println(a27ImplementStrstr.strStr("aaaaa", "bba"));
        System.out.println(a27ImplementStrstr.strStr("", ""));
        System.out.println(a27ImplementStrstr.strStr("mississippi", "issip"));
    }

    /**
     * KMP算法
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        int m = haystack.length();
        int n = needle.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            if (j == n) {
                return i - n;
            }
        }
        return -1;
    }



    /**
     * 暴力匹配
     */
    public int strStr1(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        int m = haystack.length();
        int n = needle.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            if (j == n) {
                return i - n;
            }
        }
        return -1;
    }

}
