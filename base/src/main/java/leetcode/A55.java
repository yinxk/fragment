package leetcode;

public class A55 {
    public static void main(String[] args) {
        A55 obj = new A55();
        System.out.println(obj.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(obj.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j) {
                    dp[i] |= dp[j];
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[n - 1];
    }

    /**
     *
     * 思路：尽可能到达最远位置（贪心）。
     * 如果能到达某个位置，那一定能到达它前面的所有位置。
     *
     * 方法：初始化最远位置为 0，然后遍历数组，如果当前位置能到达，并且当前位置+跳数>最远位置，就更新最远位置。最后比较最远位置和数组长度。
     *
     * 复杂度：时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
     *
     * 作者：mo-lan-4
     * 链接：https://leetcode.cn/problems/jump-game/solution/pythonji-bai-97kan-bu-dong-ni-chui-wo-by-mo-lan-4/
     * 来源：力扣（LeetCode）
     *
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int maxI = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxI >= i && i + nums[i] > maxI) {
                maxI = i + nums[i];
            }
        }
        return maxI >= nums.length - 1;
    }
}
