package leetcode;

/**
 * <a href="https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/"/>
 */
public class A26RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        A26RemoveDuplicatesFromSortedArray a26RemoveDuplicatesFromSortedArray = new A26RemoveDuplicatesFromSortedArray();
        System.out.println(a26RemoveDuplicatesFromSortedArray.removeDuplicates(new int[]{1, 1, 2}));
        System.out.println(a26RemoveDuplicatesFromSortedArray.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        System.out.println(a26RemoveDuplicatesFromSortedArray.removeDuplicates(new int[]{1, 1}));
    }

    /**
     * 看题解之后, 双指针写法, 简洁易懂, 而且, 移动次数非常少
     * 更加好, 整体上和
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1;
        int slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }


    /**
     * 第二种思路, 在前一种思路的基础上, 设置一个步数, 每一次发现了重复元素, 不先处理, 继续遍历,
     * 直到非重复元素时,进行移动
     */
    public int removeDuplicates2(int[] nums) {
        int newLen = nums.length;
        int step = 0;
        for (int i = 1; i < newLen; i++) {
            if (nums[i] == nums[i - 1]) {
                step++;
            } else if (step > 0) {
                for (int j = i; j < newLen; j++) {
                    nums[j - step] = nums[j];
                    // 为了看起来方便
                    nums[j] = -1;
                }
                newLen -= step;
                i -= step;
                step = 0;
            }
        }
        if (step > 0) {
            for (int j = newLen - 1; j < newLen; j++) {
                nums[j - step] = nums[j];
                // 为了看起来方便
                nums[j] = -1;
            }
            newLen -= step;
        }
        return newLen;
    }

    /**
     * 第一种方法, 思路简单, 每次都进行数组的移动, 这样, 会浪费非常多的性能
     */
    public int removeDuplicates1(int[] nums) {
        int newLen = nums.length;
        for (int i = 1; i < newLen; i++) {
            if (nums[i] == nums[i - 1]) {
                for (int j = i + 1; j < newLen; j++) {
                    nums[j - 1] = nums[j];
                    // 为了看起来方便
                    nums[j] = -1;
                }
                newLen--;
                i--;
            }
        }
        return newLen;
    }
}
