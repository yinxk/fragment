package leetcode;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class A28ImplementStrstr {

    public static void main(String[] args) {

        A28ImplementStrstr a28ImplementStrstr = new A28ImplementStrstr();
        System.out.println(a28ImplementStrstr.strStr("hello", "ll"));
        System.out.println(a28ImplementStrstr.strStr("aaaaa", "bba"));
        System.out.println(a28ImplementStrstr.strStr("", ""));
        System.out.println(a28ImplementStrstr.strStr("mississippi", "issip"));
    }

    /**
     * KMP算法
     * 这里求next数组, https://leetcode-cn.com/problems/implement-strstr/solution/dai-ma-sui-xiang-lu-kmpsuan-fa-xiang-jie-mfbs/
     * 对于代码随想录的讲解, 比较好
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        int[] next = getNext(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }

    private int[] getNext(String needle) {
        int len = needle.length();
        char[] s = needle.toCharArray();
        int[] next = new int[len];
        next[0] = 0;
        int i = 0;
        for (int j = 1; j < len; j++) {
            // i要保证大于0，因为下面有取i-1作为数组下标的操作
            while (i > 0 && s[i] != s[j]) {
                // 注意这里，是要找前一位的对应的回退位置了
                i = next[i - 1];
            }
            if (s[i] == s[j]) {
                i++;
            }
            next[j] = i;
        }
        return next;
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
