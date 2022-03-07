package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class A167TwoSumIiInputArrayIsSorted {

    public static void main(String[] args) {

        A167TwoSumIiInputArrayIsSorted obj = new A167TwoSumIiInputArrayIsSorted();
        System.out.println(Arrays.toString(obj.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(obj.twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(obj.twoSum(new int[]{-1, 0}, -1)));
    }

    /**
     * 双指针方法,
     * 前后各一个指针, < 目标值, 左指针增加
     * > 目标值, 右指针减小
     * 重合, 无
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        int len = numbers.length;
        int left = 0;
        int right = len - 1;
        int sum;
        while (left < right) {
            sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum > target) {
                right--;
            }
            if (sum < target) {
                left++;
            }
        }
        return null;
    }
}
