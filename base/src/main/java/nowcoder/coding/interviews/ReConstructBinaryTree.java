package nowcoder.coding.interviews;

import java.util.LinkedList;
import java.util.Queue;

/*

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

 */
public class ReConstructBinaryTree {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        // int[] pre = {1, 2, 3, 4};
        // int[] in = {4, 3, 2, 1};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        printBinaryTree(treeNode);
        
        
    }
    
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null
                || pre.length != in.length || pre.length == 0) {
            return null;
        }
        
        return reConstructBinaryTree(pre, 0, in, 0, in.length - 1);
    }
    
    public static TreeNode reConstructBinaryTree(int[] pre, int startIndex, int[] in, int inLeft, int inRight) {
        int val = pre[startIndex];
        int index = getTargetIndexFromArray(val, in, inLeft, inRight);
        TreeNode parent = new TreeNode(val);
        if (index - inLeft > 0) {
            parent.left = reConstructBinaryTree(pre, startIndex + 1, in, inLeft, index - 1);
        }
        if (inRight - index > 0) {
            parent.right = reConstructBinaryTree(pre, index - inLeft + startIndex + 1, in, index + 1, inRight);
        }
        return parent;
    }
    
    public static int getTargetIndexFromArray(int target, int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }
    
    static char space = ' ';
    static int stepLength = 4;
    
    public static void printBinaryTree(final TreeNode root) {
        int level = calBinaryTreeLevel(root);
        calNumberAndLevelInFullBinaryTree(root, 1, 1);
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode ite = root;
        queue.offer(ite);
        while (!queue.isEmpty()) {
            ite = queue.poll();
            if (ite == null) continue;
            queue.offer(ite.left);
            queue.offer(ite.right);
        }
        level = 6;
        for (int i = 0; i < level; i++) {
            int w = 1 << (level - i + 1);
            for (int j = 0; j < 1 << i; j++) {
                for (int k = 0; k < w - 1; k++) {
                    System.out.print(" ");
                }
                System.out.print("o");
                for (int k = 0; k < w; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
        }
        
        
        StringBuilder str = new StringBuilder();
        
        
        System.out.println(str.toString());
        
        
    }
    
    public static void appendXSpace(int x, StringBuilder str) {
        for (int i = 0; i < x; i++) {
            str.append(space);
        }
    }
    
    public static void calNumberAndLevelInFullBinaryTree(final TreeNode node, final int number, final int level) {
        if (node == null) return;
        node.number = number;
        node.level = level;
        
        calNumberAndLevelInFullBinaryTree(node.left, number * 2, level + 1);
        calNumberAndLevelInFullBinaryTree(node.right, number * 2 + 1, level + 1);
    }
    
    public static int calBinaryTreeLevel(final TreeNode root) {
        if (root == null) return 0;
        return Math.max(calBinaryTreeLevel(root.left) + 1, calBinaryTreeLevel(root.right) + 1);
    }
    
    public static class TreeNode {
        int val;
        /**
         * 在满二叉树中, 节点的编号
         */
        int number;
        int level;
        int x;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) {
            val = x;
        }
    }
}
