/*
 * @lc app=leetcode.cn id=83 lang=java
 *
 * [83] 删除排序链表中的重复元素
 * 
 * 题目: 删除给定排序链表中所有重复元素, 使得每个元素只出现一次
 *
 * 难度: easy
 * 
 * 思路: 1. 迭代:
 *      1 -> 1 -> 1 -> 2
 *    start      cur
 *       1 -> 2 (start.next = cur.next)
 *      2. 递归(减而治之): f(head) = f(head.next) + judge(if head.val == head.next.val)
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
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // 不用使用哨兵结点dummyHead, 无特殊情况(首节点不会被删除)
        ListNode cur = head;
        while (cur != null) {
            ListNode start = cur; // 相同值的元素中的第一个元素
            // 找到第一个与当前值相同的元素区间中的元素值不同的结点
            while (cur != null && cur.val == start.val) {
                cur = cur.next;
            }
            // 去重相同元素
            start.next = cur;
        }

        return head;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为递归栈的深度)
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}

