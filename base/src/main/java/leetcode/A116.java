package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class A116 {

    public static void main(String[] args) {

    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNodeSize = queue.size();
            for (int i = 0; i < levelNodeSize; i++) {
                Node node = queue.poll();
                if (i < levelNodeSize - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }


}
