package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class A40 {

    public static void main(String[] args) {
        A40 obj = new A40();
        System.out.println(obj.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(obj.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> comb = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfs(res, comb, 0, candidates, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> comb, int start, int[] candidates, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                comb.addLast(candidates[i]);
                dfs(res, comb, i + 1, candidates, target - candidates[i]);
                comb.removeLast();
            }
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }

    }


}
