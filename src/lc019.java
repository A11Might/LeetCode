/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第N个节点
 *
 * 题目：删除给定链表的倒数第n个节点并返回头节点
 *
 * 难度：medium
 *
 * 思路：使用双索引，快指针先走n步后，前慢指针再同时走
 *      当后指针到达链尾，前指针在倒数n + 1位置，方便删除倒数第n个节点
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // 使用dummyhead将头节点一般化
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 快指针先走n步
        ListNode fast = dummyHead;
        ListNode slow = fast;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // 当快指针到达链尾，慢指针在倒数n + 1位置
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 删除倒数第n个节点
        slow.next = slow.next.next;

        return dummyHead.next;
    }
}
