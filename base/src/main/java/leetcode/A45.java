package leetcode;

public class A45 {

    public static void main(String[] args) {
        A45 obj = new A45();
        System.out.println(obj.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(obj.jump(new int[]{2, 3, 0, 1, 4}));
    }

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j) {
                    dp[i] = dp[i] == 0 ? dp[j] + 1 : Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}
