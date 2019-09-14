/*
 * @lc app=leetcode.cn id=83 lang=java
 *
 * [83] 删除排序链表中的重复元素
 * 
 * 题目：去重给定排序的链表
 * 
 * 思路：1 -> 1 -> 1 -> 2
 *    start      cur
 *       1 -> 2 (start.next = cur.next)
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
    public ListNode deleteDuplicates(ListNode head) {
        // 不用使用dummyhead，无特殊情况(首节点不会被删除)
        ListNode cur = head;
        ListNode start = cur;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            start.next = cur.next;
            start = cur.next;
            cur = start;
        }
        
        return head;
    }
}

