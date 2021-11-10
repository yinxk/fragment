package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/4sum/
 */
public class A18_4Sum {

    public static void main(String[] args) {

        A18_4Sum a18_4Sum = new A18_4Sum();
        System.out.println(a18_4Sum.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(a18_4Sum.fourSum1(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    /**
     * 1 <= nums.length <= 200
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     * <p>
     * 题目的nums[i]的和可能溢出的情况未考虑
     * 看题解发现没有考虑到和的溢出的情况
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int a = 0; a < len; a++) {
            // 枚举的数字和上一次不同
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            for (int b = a + 1; b < len; b++) {
                // 枚举的数字和上一次不同
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                long search = (long) target - ((long) nums[a] + nums[b]);
                int d = len - 1;
                for (int c = b + 1; c < len; c++) {

                    // 枚举的数字和上一次不同
                    if (c > b + 1 && nums[c] == nums[c - 1]) {
                        continue;
                    }
                    while (c < d && (long) nums[c] + nums[d] > search) {
                        d--;
                    }
                    if (c == d) {
                        break;
                    }
                    if ((long) nums[c] + nums[d] == search) {
                        List<Integer> tup = new ArrayList<>();
                        tup.add(nums[a]);
                        tup.add(nums[b]);
                        tup.add(nums[c]);
                        tup.add(nums[d]);
                        res.add(tup);
                    }

                }
            }
        }
        return res;
    }

    /**
     * 和3sum类似, 先使用3重循环+双指针
     */
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int a = 0; a < len; a++) {
            // 枚举的数字和上一次不同
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            for (int b = a + 1; b < len; b++) {
                // 枚举的数字和上一次不同
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                int search = target - (nums[a] + nums[b]);
                int d = len - 1;
                for (int c = b + 1; c < len; c++) {
                    // 枚举的数字和上一次不同
                    if (c > b + 1 && nums[c] == nums[c - 1]) {
                        continue;
                    }
                    while (c < d && nums[c] + nums[d] > search) {
                        d--;
                    }
                    if (c == d) {
                        break;
                    }
                    if (nums[c] + nums[d] == search) {
                        List<Integer> tup = new ArrayList<>();
                        tup.add(nums[a]);
                        tup.add(nums[b]);
                        tup.add(nums[c]);
                        tup.add(nums[d]);
                        res.add(tup);
                    }

                }
            }
        }
        return res;
    }
}
