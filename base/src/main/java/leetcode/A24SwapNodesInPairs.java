package leetcode;


/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */
public class A24SwapNodesInPairs {

    public static void main(String[] args) {

        A24SwapNodesInPairs a24SwapNodesInPairs = new A24SwapNodesInPairs();
        System.out.println(a24SwapNodesInPairs.swapPairs(ListNode.as(1, 2, 3, 4)));
        System.out.println(a24SwapNodesInPairs.swapPairs(null));
        System.out.println(a24SwapNodesInPairs.swapPairs(ListNode.as(1)));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = head.next;
        int i = 0;
        while (second != null) {
            if (i % 2 == 0) {
                first.next.next = second.next;
                second.next = first.next;
                first.next = second;
                second = second.next;
            }
            first = first.next;
            second = second.next;
            i++;
        }
        return dummy.next;
    }

}
