/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 *
 * 题目：返回给定链表的入环第一个节点，如果链表无环，则返回 null
 *
 * 难度：medium
 *
 * 思路：快慢指针，快慢指针相遇时，将快指针指向头节点，以每次走一步的速度行走，再次相遇时，所在的节点就是入环第一个节点。
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head, slow = head;
        // fast到达链尾时，退出循环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 快慢指针相遇，说明有环
            if (fast == slow) break;
        }
        // 快指针到达链尾，无环
        if (fast == null || fast.next == null) return null;
        fast = head;
        // 有环，将slow置为head，fast不变，步速都为1，相遇则为入环的第一个节点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

