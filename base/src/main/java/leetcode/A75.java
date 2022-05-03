package leetcode;

import java.util.Arrays;

public class A75 {

    public static void main(String[] args) {
        A75 obj = new A75();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        obj.sortColors(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 0, 1};
        obj.sortColors(nums);
        System.out.println(Arrays.toString(nums));

    }

    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 0; i < nums.length; ) {
            for (int j = 0; j < counts.length; j++) {
                while (counts[j] > 0) {
                    nums[i] = j;
                    counts[j]--;
                    i++;
                }
            }
        }
    }


}
