/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
 *
 * 题目：判断给定链表是否是回文链表
 *
 * 难度：easy
 *
 * 思路：双索引找到中间节点，反转后半部分链表后，依次从头节点和尾节点遍历，比较值是否相等，全部相等即为回文链表
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
    public boolean isPalindrome(ListNode head) {
        boolean res = true; // 标记链表是否为回文链表，方便之后恢复链表
        ListNode fast = head;
        ListNode slow = head;
        // 找到链表中间位置
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = null;
        ListNode cur = slow;
        // 反转后半部分链表
        while (cur != null) {
            ListNode succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
        }
        // cur为后半部分链表的当前节点
        // head为前半部分链表的当前节点
        cur = pre;
        // 判断是否为回文链表
        while (cur != head && cur != null) {
            if (cur.val != head.val) {
                res = false;
                break;
            }
            cur = cur.next;
            head = head.next;
        }
        cur = pre;
        pre = null;
        // 恢复链表
        while (cur != null) {
            ListNode succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
        }
        return res;
    }
}

