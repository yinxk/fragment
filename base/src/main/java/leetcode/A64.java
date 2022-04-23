package leetcode;

public class A64 {
    public static void main(String[] args) {
        A64 obj = new A64();
        System.out.println(obj.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(obj.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    /**
     * 简单二维动态规划
     * <p>
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 设f(i,j)表示从左上角到坐标(i,j)的数字的最小和
     * <p>
     * 则 f(i,j) = min(f(i-1,j), f(i,j-1)) + grid[i,j]
     */
    public int minPathSum(int[][] grid) {
        int x = grid.length;
        int y = grid[0].length;
        int[][] dp = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[x - 1][y - 1];
    }
}
