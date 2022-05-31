package leetcode;

public class A44 {

    public static void main(String[] args) {
        A44 obj = new A44();
        System.out.println(obj.isMatch("aa", "a"));
        System.out.println(obj.isMatch("aa", "*"));
        System.out.println(obj.isMatch("cb", "?a"));
        System.out.println(obj.isMatch("adceb", "a*b"));
        System.out.println(obj.isMatch("acdcb", "a*c?b"));
    }


    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char a = s.charAt(i - 1);
                char b = p.charAt(j - 1);
                if (a == b || b == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (b == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }


}
