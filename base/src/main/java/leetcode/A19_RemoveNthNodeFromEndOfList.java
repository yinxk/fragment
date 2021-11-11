package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class A19_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        A19_RemoveNthNodeFromEndOfList a19_removeNthNodeFromEndOfList = new A19_RemoveNthNodeFromEndOfList();
        System.out.println(a19_removeNthNodeFromEndOfList.removeNthFromEnd(ListNodes.asListNode(1, 2, 3, 4, 5), 2));
        System.out.println(a19_removeNthNodeFromEndOfList.removeNthFromEnd(ListNodes.asListNode(1), 1));
        System.out.println(a19_removeNthNodeFromEndOfList.removeNthFromEnd(ListNodes.asListNode(1, 2), 1));
    }


    /*
     * 通过题解区,
     * 1. 链表题目一般可以 添加一个哑结点(dummy)在头结点之前, 这样不用特殊处理头结点
     * 2. 除了使用stack,
     * 还有计算链表长度, 在第二次遍历时 L−n+1 结点, 进行删除
     * 快慢指针, 双指针(使用两个指针, 第一个指针先遍历n次, 然后同时遍历, 直到a指针到链表末同时停止, 通过b指针进行操作)
     */

    /**
     * 快慢指针, 双指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 通过计算链表长度, 两次遍历进行操作
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        cur = dummy;
        int nodeInd = len - n + 1;
        for (int i = 1; i < nodeInd; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    /**
     * 添加哑结点 通过栈
     * 1 <= n <= sz
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head);
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = dummy;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.pop();
        pre.next = pre.next.next;
        return dummy.next;
    }

    /**
     * 单链表 通过栈来取
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) return null;
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                break;
            } else {
                temp = stack.pop();
            }
        }
        if (stack.isEmpty()) {
            head = head.next;
        } else {
            ListNode pre = stack.pop();
            pre.next = temp.next;
        }
        return head;
    }


    public static class ListNodes {

        public static ListNode asListNode(int... val) {
            ListNode pre = null;
            ListNode head = null;
            for (int i = val.length - 1; i >= 0; i--) {
                head = new ListNode(val[i], pre);
                pre = head;
            }
            return head;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("[");
            ListNode temp = this;
            boolean first = true;
            while (temp != null) {
                if (first) {
                    first = false;
                } else {
                    strBuilder.append(",");
                }
                strBuilder.append(temp.val);
                temp = temp.next;
            }
            strBuilder.append("]");
            return strBuilder.toString();
        }
    }
}
