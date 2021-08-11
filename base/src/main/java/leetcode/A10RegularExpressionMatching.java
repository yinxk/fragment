package leetcode;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * <p>
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class A10RegularExpressionMatching {

    public static void main(String[] args) {
        A10RegularExpressionMatching a10RegularExpressionMatching = new A10RegularExpressionMatching();

        // System.out.println(a10RegularExpressionMatching.isMatch("aa", "a"));
        // System.out.println(a10RegularExpressionMatching.isMatch("aa", "a*"));
        // System.out.println(a10RegularExpressionMatching.isMatch("ab", ".*"));
        System.out.println(a10RegularExpressionMatching.isMatch("aab", "c*a*b"));
        // System.out.println(a10RegularExpressionMatching.isMatch("mississippi", "mis*is*p*."));
        // System.out.println(a10RegularExpressionMatching.isMatch("aaa", "a*a"));
        // System.out.println(a10RegularExpressionMatching.isMatch("a", "ab*a"));
        // System.out.println(a10RegularExpressionMatching.isMatch("aaa", "ab*a*c*a"));

    }


    /*
     * 官方解法, 在之前尝试直接匹配和动态规划 找状态转移, 并测试, 都不是完全正确, 最后官方解法
     * 动态规划掌握程度提高
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }



    /*
     *
     * 想法是: 普通模式和变数量模式, 从后向前匹配, 最后的结果是, 只能得出部分正确结果, 所以, 该方式不行
     *
     */
    public boolean isMatch1(String s, String p) {
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return false;
        }
        int pLength = p.length();
        int sLength = s.length();

        int sIndex = sLength - 1;
        int pIndex = pLength - 1;
        boolean inMaybeCompare = false;
        while (true) {
            if (pIndex < 0 && sIndex < 0) {
                return true;
            }
            if (sIndex < 0 && pIndex > 0) {
                boolean maybeSymbol = true;
                while (pIndex > -1) {
                    char pCh = p.charAt(pIndex);
                    if (maybeSymbol) {
                        if (pCh == '*') {
                            maybeSymbol = false;
                            pIndex--;
                        } else {
                            return false;
                        }
                    } else {
                        pIndex--;
                        maybeSymbol = true;
                    }
                }
                return true;
            }
            if (pIndex < 0 || sIndex < 0) {
                return false;
            }
            char sCh = s.charAt(sIndex);
            char pCh = p.charAt(pIndex);

            if (inMaybeCompare) {
                while (sIndex > -1) {
                    sCh = s.charAt(sIndex);
                    if (pCh == sCh || pCh == '.') {
                        sIndex--;
                    } else {
                        break;
                    }
                }
                pIndex--;
                inMaybeCompare = false;
            } else {
                if (pCh != sCh && pCh != '.' && pCh != '*') {
                    return false;
                }
                if (pCh == sCh || pCh == '.') {
                    sIndex--;
                    pIndex--;
                    continue;
                }
                if (pCh == '*') {
                    inMaybeCompare = true;
                    pIndex--;
                }
            }
        }
    }
}
