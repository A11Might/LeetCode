/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [2] 两数相加
 *
 * 题目：将两个非空链表表示的非负整数相加，返回一个新的链表，链表的每个节点表示一位数字
 *      (链表节点是从低位到高位)
 * 
 * 思路：使用变量来跟踪进位，并从包含最低有效位的表头开始模拟逐位相加的过程
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy; // 返回链表头节点的哨兵节点
        int carry = 0; // 模拟进位
        // 遍历两链表，遍历完两链表时退出循环
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        // 遍历完两链表仍有进位，添加最高位
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}

