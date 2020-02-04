/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 *
 * 题目: 将两个给定的有序链表合并为一个有序链表
 *
 * 难度: easy
 *
 * 思路: 1. 迭代: 同归并排序的merge
 *      2. 递归(减而治之): f(l1, l2) = max(l1.val, l2.val) + (f(l1.next, l2) or f(l1, l2.next))
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // 使用dummy哨兵结点和tail方便维护返回的链表
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy; // 用于返回的链表的最后一个节点
        while (l1 != null || l2 != null) {
            // 当前节点为空时, 设置一个最大值从而等同于不参加比较大小
            int value1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int value2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (value1 <= value2) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        return dummy.next;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为递归栈的深度)
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}

