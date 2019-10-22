/*
 * @lc app=leetcode.cn id=445 lang=java
 *
 * [445] 两数相加 II
 *
 * 题目：将两个非空链表表示的非负整数相加，返回一个新的链表，链表的每个节点表示一位数字
 *      (链表节点是从高位到低位)
 *
 * 思路：使用栈逆序链表节点值，再使用变量来跟踪进位，并从包含最低有效位的栈顶开始模拟逐位
 *      相加的过程，最后在逆序创建新链表返回
 */

import java.util.ArrayDeque;
import java.util.Deque;

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
        // 将从高位到低位的链表，逆序为低位到高位
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        // 使用栈暂时保存相加后的结果，用于最后从高位到低位创建链表返回
        Deque<Integer> stackHelper = new ArrayDeque<>();
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        int carry = 0;
        // 遍历两链表，遍历完两链表时退出循环
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = a + b + carry;
            carry = sum / 10;
            stackHelper.push(sum % 10);
        }
        // 遍历完两链表仍有进位，添加最高位
        if (carry != 0) {
            stackHelper.push(carry);
        }

        // 从高位到低位创建链表返回
        while (!stackHelper.isEmpty()) {
            tail.next = new ListNode(stackHelper.pop());
            tail = tail.next;
        }

        return dummyHead.next;
    }
}
