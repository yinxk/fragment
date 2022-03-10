package leetcode;

import java.util.Arrays;
import java.util.Random;

public class Sort {

    public static void main(String[] args) {
        Sort sort = new Sort();
        Random random = new Random();
        int count = random.nextInt(10) + 2;
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(nums));
        sort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


    void quickSort(int[] nums, int left, int right) {
        System.out.printf(">>>>>>>>> left: %s, right: %s, \n", left, right);
        if (left >= right) {
            return;
        }
        int first = left, last = right, key = nums[left];
        while (first < last) {
            while (first < last && nums[last] >= key) {
                last--;
            }
            nums[first] = nums[last];
            System.out.printf("%s  first: %s, last: %s, key: %s \n", Arrays.toString(nums), first, last, key);
            while (first < last && nums[first] <= key) {
                first++;
            }
            nums[last] = nums[first];
            System.out.printf("%s  first: %s, last: %s, key: %s \n", Arrays.toString(nums), first, last, key);
        }
        nums[first] = key;

        System.out.printf("%s  first: %s, last: %s, key: %s , ====\n", Arrays.toString(nums), first, last, key);
        System.out.printf("<<<<<<<< left: %s, right: %s, \n", left, right);
        quickSort(nums, left, first - 1);
        quickSort(nums, first + 1, right);
    }

}
