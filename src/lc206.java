/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 * 
 * 题目：反转一个单链表
 *
 * 难度：easy
 * 
 * 思路：1. 迭代：使用三个指针pre, cur 和 succ
 *      2. 递归（减而治之）：f(head) = f(head.next) + 反转 head
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
    public ListNode reverseList1(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, cur = head, succ = null;
        while (cur != null) {
            succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
        }

        return pre;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) (n 为递归栈的深度)
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode succ = head.next;
        ListNode newHead = reverseList(succ);
        succ.next = head;
        head.next = null;
        return newHead;
    }
}

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

