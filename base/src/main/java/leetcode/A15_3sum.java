package leetcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 */
public class A15_3sum {

    public static void main(String[] args) throws IOException {

        A15_3sum a15_3sum = new A15_3sum();
        System.out.println(a15_3sum.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(a15_3sum.threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0}));
    }

    /**
     * 最容易想到的办法, 穷举法
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return res;
        }
        Set tupleSet = new Set();
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

    public static class Set {

        List<Tuple> values = new ArrayList<>();

        public void add(Tuple val) {
            values.add(val);
        }

        public boolean contains(Tuple val) {
            for (Tuple value : values) {
                if (value.equals(val)) {
                    return true;
                }
            }
            return false;
        }

    }

    public static class Tuple {

        int[] values;

        public Tuple(int a, int b, int c) {
            this.values = new int[3];
            this.values[0] = a;
            this.values[1] = b;
            this.values[2] = c;
        }

        public boolean equals(Tuple o) {
            if (o == null) {
                return false;
            }
            int len = values.length;
            int[] comp = new int[len];
            boolean[] comped = new boolean[len];
            System.arraycopy(values, 0, comp, 0, len);
            for (int i = 0; i < len; i++) {
                int it = o.values[i];
                for (int j = 0; j < len; j++) {
                    if (!comped[j] && it == comp[j]) {
                        comped[j] = true;
                    }
                }
            }
            for (boolean b : comped) {
                if (!b) {
                    return false;
                }
            }
            return true;
        }

    }

}
