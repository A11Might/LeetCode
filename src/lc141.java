/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 * 
 * 题目：判断给定链表是否有环
 *
 * 难度：easy
 *
 * 思路：快慢指针，一个指针每次移动一个节点，一个指针每次移动两个节点，如果存在环，那么这两个指针一定会相遇。
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
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head, slow = head;
        // fast到达链尾时, 表示链表无环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 快慢指针相遇
            if (fast == slow) return true;
        }

        return false;
    }
}