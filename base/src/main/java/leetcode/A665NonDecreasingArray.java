package leetcode;

/**
 * https://leetcode-cn.com/problems/non-decreasing-array/
 */
public class A665NonDecreasingArray {

    public static void main(String[] args) {
        A665NonDecreasingArray obj = new A665NonDecreasingArray();
        System.out.println(obj.checkPossibility(new int[]{4, 2, 3}));
        System.out.println(obj.checkPossibility(new int[]{4, 2, 1}));

    }

    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int len = nums.length;
        // 标识是否还能修改
        boolean flag = nums[0] <= nums[1];
        for (int i = 1; i < len - 1; i++) {
            // 出现递减
            if (nums[i] > nums[i + 1]) {
                // 如果还有修改机会，进行修改
                if (flag) {
                    // 修改方案1
                    if (nums[i + 1] >= nums[i - 1]) nums[i] = nums[i + 1];
                        // 修改方案2
                    else nums[i + 1] = nums[i];
                    // 用掉唯一的修改机会
                    flag = false;
                }
                // 没有修改机会，直接结束
                else return false;
            }
        }
        return true;
    }
}
