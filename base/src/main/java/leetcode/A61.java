package leetcode;

public class A61 {


    public static void main(String[] args) {
        A61 obj = new A61();
        System.out.println(obj.rotateRight(ListNode.as(1, 2, 3, 4, 5), 2));
        System.out.println(obj.rotateRight(ListNode.as(1), 0));
        System.out.println(obj.rotateRight(ListNode.as(1, 2), 1));
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < k; i++) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                fast = head;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (slow.next != null) {
            head = slow.next;
            slow.next = null;
            fast.next = dummy.next;
        }
        return head;
    }
}
