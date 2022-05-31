package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class A46 {
    public static void main(String[] args) {
        A46 obj = new A46();
        System.out.println(obj.permute(new int[]{1, 2, 3}));
        System.out.println(obj.permute(new int[]{0, 1}));
        System.out.println(obj.permute(new int[]{1}));
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        dfs1(nums, 0, visited, path, res);
        return res;
    }

    private void dfs1(int[] nums, int index, boolean[] visited, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            path.addLast(nums[i]);
            dfs1(nums, index, visited, path, res);
            visited[i] = false;
            path.removeLast();
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res);
        return res;
    }

    private void dfs(int[] nums, int level, List<List<Integer>> res) {
        if (level == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            res.add(path);
            return;
        }
        for (int i = level; i < nums.length; i++) {
            swap(nums, level, i);
            dfs(nums, level + 1, res);
            swap(nums, level, i);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
