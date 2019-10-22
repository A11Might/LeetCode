/**
 * @author qhhu
 * @date 2019/9/20 - 14:40
 *
 * [61] 旋转链表
 *
 * 题目：将给定链表的每个节点向右移动k个节点(k是非负数)
 *
 * 难度：medium
 *
 * 思路：(1 -> 2 -> ... -> k - 1) -> (k -> k + 1 -> ... -> n)
 *
 *  ===>(k -> k + 1 -> ... -> n) -> (1 -> 2 -> ... -> k - 1)
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 将k转化为有效移动位数
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        k %= count;
        // 无须移动则直接返回
        if (k == 0 || count == k) {
            return head;
        }
        // 找到倒数第k + 1个节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = fast;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow为倒数第k + 1个节点
        // 重组链表
        head = slow.next;
        slow.next = null;
        fast.next = dummyHead.next;

        return head;
    }
}
