package nowcoder.coding.interviews;

/*

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

 */
public class ReConstructBinaryTree {
    public static void main(String[] args) {
        // int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        // int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] pre = {1, 2, 3, 4};
        int[] in = {4, 3, 2, 1};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        System.out.println(treeNode);
        
        
    }
    
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null
                || pre.length != in.length || pre.length == 0) {
            return null;
        }
        int index = 0;
        TreeNode head = new TreeNode(pre[index]);
        int valIndex = getTargetIndexFromArray(pre[0], in, 0, in.length - 1);
        if (valIndex > 0) {
            index = reConstructBinaryTree(head, pre, in, index + 1, 0, valIndex - 1, valIndex);
        }
        if (in.length - 1 - valIndex > 0) {
            reConstructBinaryTree(head, pre, in, index + 1, valIndex + 1, in.length - 1, valIndex);
        }
        return head;
    }
    
    public static int reConstructBinaryTree(TreeNode parent, int[] pre, int[] in, int index, int inLeft, int inRight, int bound) {
        int valIndex = getTargetIndexFromArray(pre[index], in, inLeft, inRight);
        TreeNode next = new TreeNode(pre[index]);
        if (valIndex < bound) {
            parent.left = next;
        } else {
            parent.right = next;
        }
        if (valIndex - inLeft > 0) {
            index = reConstructBinaryTree(next, pre, in, index + 1, inLeft, valIndex - 1, valIndex);
        }
        if (inRight - valIndex > 0) {
            return reConstructBinaryTree(next, pre, in, index + 1, valIndex + 1, inRight, valIndex);
        }
        return index;
    }
    
    public static int getTargetIndexFromArray(int target, int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }
    
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) {
            val = x;
        }
    }
}
