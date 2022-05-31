package leetcode;

public class A35 {

    public static void main(String[] args) {
        A35 obj = new A35();
        System.out.println(obj.searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(obj.searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(obj.searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(obj.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        int pos = right + 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target <= nums[mid]) {
                right = mid - 1;
                pos = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }
}
