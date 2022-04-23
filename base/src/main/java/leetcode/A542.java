package leetcode;

public class A542 {
    public static void main(String[] args) {
        A542 obj = new A542();
        print(obj.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }


    /**
     * 设f(i,j) = 到0的最近距离,
     * 则: f(i,j) = min(f(i-1,j), f(i,j-1), f(i,j-1), f(i,j+1)) + 1
     * 分解:
     * <p>
     * 从左上点到右下点: f(i,j) = min(f(i-1,j), f(i,j-1)) + 1
     * 从右下点到左上点:  f(i,j) = min(min(f(i+1,j), f(i,j+1)) + 1, f(i,j))
     */
    public int[][] updateMatrix(int[][] mat) {
        int x = mat.length;
        int y = mat[0].length;
        int[][] dp = new int[x][y];
        // 设定初始值, 设为最大为 10_0000, 由于(1 <= m * n <= 10^4)
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                dp[i][j] = 10_0000;
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (mat[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }

                }
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (i < x - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }
                    if (j < y - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                    }
                }
            }
        }
        return dp;
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[j][j] + "\t");
            }
            System.out.println();
        }
    }
}
