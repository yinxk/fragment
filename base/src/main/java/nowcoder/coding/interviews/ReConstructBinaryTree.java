package nowcoder.coding.interviews;

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
        System.out.println(treeNode);
        
        
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
    
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) {
            val = x;
        }
    }
}
