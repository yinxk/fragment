package leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreesWithSimilarLeaves {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> res1 = new ArrayList<>();
        pre(root1, res1);
        List<Integer> res2 = new ArrayList<>();
        pre(root2, res2);

        if (res1.size() != res2.size()){
            return false;
        }
        int size = res1.size();

        for(int i = 0 ; i < size; i++){
            Integer i1 = res1.get(i);
            Integer i2 = res2.get(i);
            if (!i1.equals(i2)){
                return false;
            }
        }
        return true;
    }

    public void pre(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            res.add(root.val);
            return;
        }
        pre(root.left, res);
        pre(root.right, res);
    }

}
