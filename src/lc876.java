/*
 * @lc app=leetcode.cn id=876 lang=java
 *
 * [876] 链表的中间结点
 * 
 * 快慢指针：快指针到达链尾，慢指针在链表中间位置
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
    public ListNode middleNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

