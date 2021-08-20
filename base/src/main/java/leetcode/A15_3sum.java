package leetcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/3sum/
 */
public class A15_3sum {

    public static void main(String[] args) throws IOException {

        A15_3sum a15_3sum = new A15_3sum();
        System.out.println(a15_3sum.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 最容易想到的办法
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return res;
        }
        Set<Tuple> tupleSet = new HashSet<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Tuple tuple = new Tuple(nums[i], nums[j], nums[k]);
                        if (!tupleSet.contains(tuple)) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                            tupleSet.add(tuple);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static class Tuple {

        public Integer a;
        public Integer b;
        public Integer c;

        public Tuple(Integer a, Integer b, Integer c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            return hashCode() == o.hashCode();
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }

}
