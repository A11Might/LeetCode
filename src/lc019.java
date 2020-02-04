/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第N个节点
 *
 * 题目: 删除给定链表的倒数第n个节点并返回头节点
 *
 * 难度: medium
 *
 * 思路: 使用双指针, 两指针都指向链表头结点, 前指针先走n步后, 后指针再同时走
 *      当后指针到达链尾时, 前指针在倒数n + 1位置, 方便删除倒数第n个节点
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        // 使用哨兵结点dummyHead将头节点一般化(方便删除头结点)
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 前指针先走n步
        ListNode pre = dummy, succ = dummy;
        while (n-- > 0) {
            pre = pre.next;
        }
        // 当前指针到达链尾时, 后指针在倒数n + 1位置
        while (pre.next != null) {
            pre = pre.next;
            succ = succ.next;
        }
        // 删除倒数第n个节点
        succ.next = succ.next.next;

        return dummy.next;
    }
}
