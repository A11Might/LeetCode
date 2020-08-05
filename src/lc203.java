/*
 * @lc app=leetcode.cn id=203 lang=java
 *
 * [203] 移除链表元素
 *
 * 题目：删除链表中所有等于给定值的节点
 * 
 * 难度：easy
 *
 * 思路：从虚拟头结点开始遍历，当下一个节点的值等于 val 时，就删除下一个节点删除
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1); // 使用虚拟头节点，将删除头节点的特殊情况一般化
        dummy.next = head;
        for (ListNode cur = dummy; cur != null; ) {
            if (cur.next != null && cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}