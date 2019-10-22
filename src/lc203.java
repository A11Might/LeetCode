/*
 * @lc app=leetcode.cn id=203 lang=java
 *
 * [203] 移除链表元素
 *
 * 题目：删除链表中所有等于给定值的节点
 *
 * 思路：使用虚拟头节点，将删除头节点的特殊情况一般化
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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (head != null) {
            // 跳过所有连续等于val的节点
            while (head != null && head.val == val) {
                head = head.next;
            }
            pre.next = head;
            pre = head;
            // 可能跳完链表(放置空指针)
            if (head != null) {
                head = head.next;
            }
        }

        return dummyHead.next;
    }
}

