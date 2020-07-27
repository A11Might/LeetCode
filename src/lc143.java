/**
 * @author qhhu
 * @date 2019/9/20 - 15:09
 *
 * [143] 重排链表
 *
 * 题目：给定一个单链表 L：L0→L1→…→Ln-1→Ln
 *      将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 难度：medium
 *
 * 思路：快慢指针找到中间节点，反转后半部分链表后，依次从头和尾遍历，重组链表
 *      注：方法一和方法二的区别，就是有没有将前后两链表从中间断开。
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null) return;
        // 把链表从中间分成两部分
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        // 逆序后半部分链表
        head2 = reverseList(head2);
        // 将两链表重新拼接
        ListNode dummy = new ListNode(-1), tail = dummy;
        while (head != null || head2 != null) {
            if (head != null) {
                tail.next = head;
                tail = head;
                head = head.next;
            }
            if (head2 != null) {
                tail.next = head2;
                tail = head2;
                head2 = head2.next;
            }
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode succ = head.next;
        ListNode newHead = reverseList(succ);
        succ.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;
        // 找到中间节点
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转后半部分链表
        ListNode pre = null, succ = null;
        while (slow != null) {
            succ = slow.next;
            slow.next = pre;
            pre = slow;
            slow = succ;
        }
        // 在dummyhead节点后，重组链表
        ListNode dummyHead = new ListNode(0), tail = dummyHead;
        // pre为后半部分链表的当前节点，head为前半部分链表的当前节点
        //                                              null
        //                                               ^
        //                                               |
        // 当整个链表有奇数个节点，结束条件为pre == head(1 -> 2 <- 3)
        //                                                  null
        //                                                   ^
        //                                                   |
        // 当整个链表有偶数个节点，结束条件为pre == null(1 -> 2 -> 3 <- 4)
        while (pre != null && pre != head) {
            tail.next = head;
            head = head.next;
            tail.next.next = pre;
            pre = pre.next;
            tail = tail.next.next;
        }
    }
}
