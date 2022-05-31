package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class A47 {
    public static void main(String[] args) {
        A47 obj = new A47();
        System.out.println(obj.permuteUnique(new int[]{1, 1, 2}));
        System.out.println(obj.permuteUnique(new int[]{1, 2, 3}));
        System.out.println(obj.permuteUnique(new int[]{1}));
    }

    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(nums);
        dfs1(nums, visited, path, res);
        return res;
    }

    private void dfs1(int[] nums, boolean[] visited, Deque<Integer> path, List<List<Integer>> res) {
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
            dfs1(nums, visited, path, res);
            visited[i] = false;
            path.removeLast();
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
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
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
