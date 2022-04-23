package leetcode;

public class A70 {
    public static void main(String[] args) {
        A70 obj = new A70();
        System.out.println(obj.climbStairs(1));
        System.out.println(obj.climbStairs(2));
        System.out.println(obj.climbStairs(3));
        System.out.println(obj.climbStairs(12));
    }

    /**
     * 简单一维动态规划
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 设 f(i) = 到第i阶的方法数
     * 则 f(i) = f(i-1) + f(i-2)
     */
    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 由于 f(i) = f(i-1) + f(i-2),只与前两个值有关
     * 可以使用变量来代替数组
     */
    public int climbStairs(int n) {
        if (n <= 0) return -1;
        if (n <= 2) {
            return n;
        }
        int prev2 = 1;
        int prev1 = 2;
        int curr = 0;
        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;

    }
}
