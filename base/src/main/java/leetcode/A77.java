package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class A77 {

    public static void main(String[] args) {
        A77 obj = new A77();
        System.out.println(obj.combine(4, 2));
        System.out.println(obj.combine(1, 1));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        Stack<Integer> eachAns = new Stack<>();
        backTrack(n, 0, k, eachAns, ans);
        return ans;
    }

    private void backTrack(int n, int start, int k, Stack<Integer> eachAns, List<List<Integer>> ans) {
        if (eachAns.size() == k) {
            ans.add(new ArrayList<>(eachAns));
            return;
        }
        for (int i = start; i < n; i++) {
            eachAns.push(i + 1);
            backTrack(n, i + 1, k, eachAns, ans);
            eachAns.pop();
        }
    }

}
