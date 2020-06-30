package leetcode;

/**
 * https://www.youtube.com/watch?v=LPFhl65R7ww&t=1013s
 */
public class MedianOfTwoSortedArrays {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int x = nums1.length;
        int y = nums2.length;

        if (x > y) {
            return findMedianSortedArrays(nums2, nums1);
        }


        int low = 0;
        int hight = x;

        while (low <= hight) {

            int px = (low + hight) / 2;
            int py = (x + y + 1) / 2 - px;

            int maxLeftX = px == 0 ? Integer.MIN_VALUE : nums1[px - 1];
            int minRightX = px == x ? Integer.MAX_VALUE : nums1[px];

            int maxLeftY = py == 0 ? Integer.MIN_VALUE : nums2[py - 1];
            int minRightY = py == y ? Integer.MAX_VALUE : nums2[py];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                hight = px - 1;
            } else {
                low = px + 1;
            }
        }

        throw new RuntimeException();

    }

    public static void main(String[] args) {


        int[] x = {1, 3};
        int[] y = {2};
        MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
        System.out.println(obj.findMedianSortedArrays(x, y));

        x = new int[]{1, 2};
        y = new int[]{3, 4};
        obj = new MedianOfTwoSortedArrays();
        System.out.println(obj.findMedianSortedArrays(x, y));
    }
}
