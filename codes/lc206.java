/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 * 
 * 题目：反转一个单链表
 * 
 * 思路：1、迭代：使用三个指针pre、cur和succ
 *      2、递归：f(head) = 反转head和head.next之间的链式结构 + f(head.next)
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
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode succ = null;
        while (cur != null) {
            succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
        }

        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        return process(head);
    }

    private ListNode process(ListNode node) {
        if (node.next == null) {
            return node;
        }
        ListNode res = process(node.next);
        node.next.next = node;
        node.next = null;

        return res;
    }
}

