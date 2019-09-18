/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] k个一组翻转链表
 *
 * 题目：k个一组翻转给定链表的节点
 *
 * 难度：hard
 * 
 * 思路：递归，f(n) = f(n - k) + 反转k个节点
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tail = head; // tail当前链表第k个元素
        int count = 1;
        // 从头数k个节点
        // tail.next != null，保证数出k个节点
        // (tail != null，虽然可以tail = tail.next，但可能让tail == null)
        while (tail.next != null && count < k) {
            tail = tail.next;
            count++;
        }
        // 有k个节点
        if (count == k) {
            tail.next = reverseKGroup(tail.next, k);
            ListNode cur = head;
            ListNode succ = cur.next;
            while (cur != tail) {
                cur.next = tail.next;
                tail.next = cur;
                cur = succ;
                succ = cur.next;
            }
            return tail;
        // 不够k个
        } else {
            return head;
        }
    }
}

