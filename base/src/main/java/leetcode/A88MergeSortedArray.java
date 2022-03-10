package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class A88MergeSortedArray {

    public static void main(String[] args) {
        A88MergeSortedArray obj = new A88MergeSortedArray();
        int[] a = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] b = new int[]{2, 5, 6};
        int n = 3;
        obj.merge(a, m, b, n);
        System.out.println(Arrays.toString(a));

        a = new int[]{1};
        m = 1;
        b = new int[]{};
        n = 0;
        obj.merge(a, m, b, n);
        System.out.println(Arrays.toString(a));

        a = new int[]{0};
        m = 0;
        b = new int[]{1};
        n = 1;
        obj.merge(a, m, b, n);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 双指针解法
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = m + n - 1;
        int cur1 = m - 1;
        int cur2 = n - 1;
        while (cur2 >= 0) {
            if (cur1 >= 0 && nums1[cur1] >= nums2[cur2]) {
                nums1[cur] = nums1[cur1];
                cur1--;
            } else {
                nums1[cur] = nums2[cur2];
                cur2--;
            }
            cur--;
        }
    }
}
