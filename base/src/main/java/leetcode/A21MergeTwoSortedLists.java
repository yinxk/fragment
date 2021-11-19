package leetcode;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class A21MergeTwoSortedLists {

    public static void main(String[] args) {


        A21MergeTwoSortedLists a21MergeTwoSortedLists = new A21MergeTwoSortedLists();
        System.out.println(a21MergeTwoSortedLists.mergeTwoLists(ListNode.as(1, 2, 4), ListNode.as(1, 3, 4)));
        System.out.println(a21MergeTwoSortedLists.mergeTwoLists(null, ListNode.as(0)));
        System.out.println(a21MergeTwoSortedLists.mergeTwoLists(null, null));
    }

    /**
     * 合并时, 在处理某一个链表为空的时候, 冗余了步骤
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        cur.next = cur1 == null ? cur2 : cur1;
        return dummy.next;
    }

    /**
     * 第一步想法, 直接合并
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null && cur2 != null) {
                if (cur1.val <= cur2.val) {
                    cur.next = cur1;
                    cur1 = cur1.next;
                } else {
                    cur.next = cur2;
                    cur2 = cur2.next;
                }
            } else if (cur1 != null) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
