package leetcode;

public class A53 {

    public static void main(String[] args) {
        A53 obj = new A53();
        System.out.println(obj.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(obj.maxSubArray(new int[]{1}));
        System.out.println(obj.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
