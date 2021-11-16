package leetcode;

/**
 * https://leetcode-cn.com/problems/remove-element/
 */
public class A27RemoveElement {

    public static void main(String[] args) {

        A27RemoveElement a27RemoveElement = new A27RemoveElement();
        System.out.println(a27RemoveElement.removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println(a27RemoveElement.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    /**
     * 通过上一题, 优先考虑双指针的方法
     */
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int fast = 0, slow = 0;
        while (fast < n) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
