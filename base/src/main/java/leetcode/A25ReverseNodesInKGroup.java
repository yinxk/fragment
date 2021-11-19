package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= s
 */
public class A25ReverseNodesInKGroup {

    public static void main(String[] args) {
        A25ReverseNodesInKGroup a25ReverseNodesInKGroup = new A25ReverseNodesInKGroup();
        System.out.println(a25ReverseNodesInKGroup.reverseKGroup(ListNode.as(1, 2, 3, 4, 5), 3));
    }

    /**
     * 这里写的reverse不是很好, 使用了额外的空间
     * 并且, 将节点先都打散了一遍
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; ; i++) {
            if (second != null) {
                second = second.next;
                if (i % k == 0 && second != null) {
                    ListNode subHead = first.next;
                    first.next = null;
                    ListNode nextHead = second.next;
                    second.next = null;
                    ListNode[] headTail = reverse(subHead, second);
                    first.next = headTail[0];
                    headTail[1].next = nextHead;
                    first = headTail[1];
                    second = headTail[1];
                }
            } else {
                break;
            }
        }

        return dummy.next;
    }

    private ListNode[] reverse(ListNode first, ListNode second) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = first;
        ListNode pre = null;
        while (cur != null && cur != second) {
            stack.push(cur);
            pre = cur;
            cur = cur.next;
            pre.next = null;
        }
        cur = second;
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        return new ListNode[]{second, first};
    }

}
