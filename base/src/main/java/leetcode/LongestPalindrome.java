package leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s1 = "babad";
        System.out.println(longestPalindrome2(s1));

    }

    //region 初步想法, 暴力法, 挨着挨着判断是否是回文, 并记录位置, 时间复杂度太高
    public static String longestPalindrome1(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] ch = s.toCharArray();
        int length = ch.length;
        int longestPalindromeLength = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (j - i + 1 >= longestPalindromeLength && isPalindrome1(ch, i, j)) {
                    start = i;
                    end = j;
                    longestPalindromeLength = j - i + 1;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static boolean isPalindrome1(char[] ch, int start, int end) {
        while (start <= end) {
            if (ch[start] != ch[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    //endregion


    //region 经过查看题目的题解, 可以使用动态规划
    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    //endregion


}
