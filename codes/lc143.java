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
 * 思路：双索引找到中间节点，反转后半部分链表后，依次从头和尾遍历，重组链表
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
    public void reorderList(ListNode head) {
        // 链表节点个数小于3个，无需操作
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 找到中间节点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转后半部分链表
        ListNode pre = null;
        ListNode succ = null;
        while (slow != null) {
            succ = slow.next;
            slow.next = pre;
            pre = slow;
            slow = succ;
        }
        // 在dummyhead节点后，重组链表
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        // pre为后半部分链表的当前节点，head为前半部分链表的当前节点
        //                                                null
        //                                                 ^
        //                                                 |
        // 当整个链表有奇数个节点，结束条件为pre == head(1 -> 2 <- 3)
        //                                                     null
        //                                                      ^
        //                                                      |
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
