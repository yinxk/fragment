package nowcoder.coding.interviews;

import java.util.ArrayList;
import java.util.Arrays;

import leetcode.ListNode;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode pre = head;
        ListNode next;
        for (int i = 2; i <= 100; i++) {
            next = new ListNode(i);
            pre.next = next;
            pre = next;
        }
        ArrayList<Integer> integers = printListFromTailToHead(head);
        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }
        System.out.println();
        
    }
    
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        int length = 0;
        ListNode temp = listNode;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        Integer[] res = new Integer[length];
        temp = listNode;
        for (int i = length - 1; i >= 0 && temp != null; i--) {
            res[i] = temp.val;
            temp = temp.next;
        }
        ArrayList<Integer> result = new ArrayList<>(length);
        result.addAll(Arrays.asList(res));
        return result;
    }
}
