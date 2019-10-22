/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 *
 * 题目：两两交换给定链表的相邻节点
 *
 * 难度：medium
 *
 * 思路：1、迭代：使用虚拟头节点将头节点的操作一般化
 *       2、递归：f(head) = f(head.next.next) + head和head.next交换
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode first = head; // 相邻两节点前一个
        ListNode second = null; // 相邻两节点后一个
        // 有相邻两个节点，则交换位置
        while (first != null && first.next != null) {
            second = first.next;
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
            first = first.next;
        }

        return dummyHead.next;
    }

    public ListNode swapPairs2(ListNode head) {
        return process(head);
    }

    private ListNode process(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = process(next.next);
        next.next = head;
        return next;
    }
}

