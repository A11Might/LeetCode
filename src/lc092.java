/*
 * @lc app=leetcode.cn id=92 lang=java
 *
 * [92] 反转链表 II
 * 
 * 题目：反转从位置 m 到 n 的链表(要求时间复杂度O(n))
 * 
 * 思路：1、迭代，将链表分为三个部分，[0, m - 1], [m, n], [n + 1, length - 1]，反转[m, n]部分，再将三个部分连接起来
 *       2、递归，类似双索引反转字符串，牛逼，但它需要修改节点的值 https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode/
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
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode succ = null;
        // 找到m，cur最终在m位置
        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        // con为m - 1位置
        // tail为m位置，反转后是中间部分的链尾，用于连接第二第三部分
        ListNode con = pre;
        ListNode tail = cur;
        // 反转[m, n]部分，pre最终在n位置，cur最终在n + 1位置，相当于cur == null
        while (n > 0) {
            succ = cur.next;
            cur.next = pre;
            pre = cur;
            cur = succ;
            n--;
        }
        // 链接三个部分
        if (con != null) {
            con.next = pre;
        } else {
            head = pre;
        }
        tail.next = cur;
        
        return head;
    }
}

