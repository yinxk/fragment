package leetcode;

public class A86 {

    public static void main(String[] args) {
        A86 obj = new A86();
        System.out.println(obj.partition(ListNode.as(1, 4, 3, 2, 5, 2), 3));
        System.out.println(obj.partition(ListNode.as(2, 1), 2));
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
