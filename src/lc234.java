/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
 *
 * 题目: 判断给定链表是否是回文链表
 *      (要求时间复杂度: O(n), 空间复杂度: O(1))
 *
 * 难度: easy
 *
 * 思路: 双索引找到中间节点, 反转后半部分链表后, 依次从头节点和尾节点遍历, 比较值是否相等, 全部相等则为回文链表
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        boolean flag = true; // 标记链表是否为回文链表(当发现链表非回文, 恢复链表后再返回flag)
        // 找到链表中间位置
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转后半部分链表
        ListNode tail = reverseList(slow);
        ListNode recoverNode = tail; // 用于恢复后半部分链表
        // 判断是否为回文链表
        while (tail != null) {
            if (head.val != tail.val) {
                flag = false;
                break;
            }
            head = head.next;
            tail = tail.next;
        }

        // 恢复后半部分链表
        reverseList(recoverNode);

        return flag;
    }

    private ListNode reverseList(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode head = reverseList(node.next);
        node.next.next = node;
        node.next = null;

        return head;
    }
}