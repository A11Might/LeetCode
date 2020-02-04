/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 * 
 * 题目: 反转一个单链表
 *
 * 难度: easy
 * 
 * 思路: 1. 迭代: 使用三个指针pre, cur和succ
 *      2. 递归(减而治之): f(head) = 反转head和head.next之间的链式结构 + f(head.next)
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
    public ListNode reverseList(ListNode head) {
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
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为递归栈的深度)
     */
    public ListNode reverseList2(ListNode head) {
        return process(head);
    }

    // 反转链表 = 反转当前节点 + 反转剩余节点
    private ListNode process(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = process(head.next);
        head.next.next = head;
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

