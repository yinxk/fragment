package leetcode;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class A23MergeKSortedLists {

    public static void main(String[] args) {

        A23MergeKSortedLists a23MergeKSortedLists = new A23MergeKSortedLists();
        System.out.println(a23MergeKSortedLists.mergeKLists(ListNode.as(new int[]{1, 4, 5}, new int[]{1, 3, 4},
                new int[]{2, 6})));
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {

        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) >> 1;
        return merge(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    /**
     * 第一种想法, 使用归并的思想, 写法存在问题
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeListNode1(lists, 0, lists.length - 1);
    }

    /**
     * 这个分割也太怪异了吧
     */
    public ListNode mergeListNode1(ListNode[] lists, int start, int end) {
        if (end - start >= 2) {
            return merge(mergeListNode1(lists, start + 1, end - 1), merge(lists[start], lists[end]));
        } else if (end - start == 1) {
            return merge(lists[start], lists[end]);
        } else {
            return lists[start];
        }
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode first = a;
        ListNode second = b;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                cur.next = first;
                first = first.next;
            } else {
                cur.next = second;
                second = second.next;
            }
            cur = cur.next;
        }
        cur.next = first == null ? second : first;
        return dummy.next;
    }

}
