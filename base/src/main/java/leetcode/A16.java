package leetcode;

import java.util.Arrays;

public class A16 {

    public static void main(String[] args) {
        A16 obj = new A16();
        // System.out.println(obj.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        // System.out.println(obj.threeSumClosest(new int[]{0, 0, 0}, 1));
        System.out.println(obj.threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1));
    }


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int best = 5000;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return best;
    }
}
