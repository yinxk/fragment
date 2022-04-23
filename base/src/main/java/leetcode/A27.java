package leetcode;

public class A27 {

    public static void main(String[] args) {
        A27 obj = new A27();
        System.out.println(obj.removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println(obj.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
