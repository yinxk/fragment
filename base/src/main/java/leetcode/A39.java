package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A39 {

    public static void main(String[] args) {
        A39 obj = new A39();
        System.out.println(obj.combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(obj.combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(obj.combinationSum(new int[]{2}, 1));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        dfs(res, comb, 0, candidates, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> comb, int start, int[] candidates, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        if (start + 1 < candidates.length) {
            dfs(res, comb, start + 1, candidates, target);
        }
        if (target - candidates[start] >= 0) {
            comb.add(candidates[start]);
            dfs(res, comb, start, candidates, target - candidates[start]);
            comb.remove(comb.size() - 1);
        }
    }


}
