/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 *
 * 题目: 两两交换给定链表的相邻节点
 *      (不能单纯的改变节点内部的值)
 *
 * 难度: medium
 *
 * 思路: 1. 迭代: 使用虚拟头节点将头节点的操作一般化
 *      2. 递归: f(head) = f(head.next.next) + head和head.next交换
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode tail = dummyHead; // 链表相邻接点已反转部分的最后最后一个结点
        ListNode first = head, second = null; // 相邻的两个结点
        while (first != null && first.next != null) {
            // 交换相邻的两个结点的位置
            second = first.next;
            tail.next = second;
            first.next = second.next;
            second.next = first;
            tail = first;
            // 移向下一个相邻接点的前一个节点
            first = first.next;
        }

        return dummyHead.next;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为递归栈的深度)
     */
    public ListNode swapPairs2(ListNode head) {
        return process(head);
    }

    private ListNode process(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode succ = node.next;
        node.next = process(node.next.next);
        succ.next = node;

        return succ;
    }
}

