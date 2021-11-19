package leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode as(int... val) {
        ListNode pre = null;
        ListNode head = null;
        for (int i = val.length - 1; i >= 0; i--) {
            head = new ListNode(val[i], pre);
            pre = head;
        }
        return head;
    }

    public static ListNode[] as(int[]... val) {
        ListNode[] res = new ListNode[val.length];
        for (int i = 0; i < val.length; i++) {
            res[i] = as(val[i]);
        }
        return res;
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