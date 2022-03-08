package leetcode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class A142LinkedListCycleIi {

    public static void main(String[] args) {
        A142LinkedListCycleIi obj = new A142LinkedListCycleIi();
        ListNode head = ListNode.as(3, 2, 0, -4);
        ListNode cur1 = head;
        ListNode cur2 = head;
        cur1 = cur1.next;
        cur2 = cur2.next.next.next;
        cur2.next = cur1;
        ListNode listNode = obj.detectCycle(head);
        System.out.println("--");
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 判定是否存在环路
        do {
            // 这里的写法就比下面的写法更加清晰
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        // 这里已经判定存在环路
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 这里已经知道是快慢指针解法:
     * 检测是否有环:
     * 1. fast和 slow 从链表头开始
     * 2. fast 每次前进两步, slow每次前进一步
     * 3. 如果fast可以走到尽头, 没有环
     * 如果fast可以无限走下去, 在 fast和slow第一次相遇时, 判定为存在环路
     * 4. 第一次相遇时, 将fast置于链表头
     * 5. fast和slow每次都前进一步, 再次相遇, 相遇的结点为环路的开始点
     *
     * 自己的写法还是啰嗦以及存在一定问题, 测试用例没有完全通过
     */
    public ListNode detectCycle1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 这里的判定条件 slow也是多余的
        while (fast != null && slow != null) {
            // 这里的判定条件多余了一个.next.next, 这个值不用判定, 而且不能在这次判定
            if (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                return null;
            }
            if (fast == slow) {
                fast = head;
                break;
            }
        }
        // 这里的判定条件 等价于 true
        // 写的太啰嗦
        // 这里 是否 为 null判定条件是多余的
        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast == slow) {
                return fast;
            }
        }
        return null;
    }


    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            // 这样判定也存在问题, fast !=null 和 fast.next != null 分开了, 可能造成下一个循环的死循环
            if (fast.next != null ) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                return null;
            }
            if (fast == slow) {
                fast = head;
                break;
            }
        }
        // 这里的判定条件 等价于 true
        // 写的太啰嗦
        // 这里 是否 为 null判定条件是多余的
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


}
