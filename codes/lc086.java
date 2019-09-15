/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [86] 分割链表
 * 
 * 题目：对给定链表进行分隔，所有小于x的节点都在大于或等于x的节点之前
 *      (保持两个分区中每个节点的初始相对位置)
 * 
 * 思路：类似快排的partition，使用虚拟节点将链表分为小于和等于大于两个链表
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
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        // 小于部分的虚拟头节点
        ListNode smallHead = new ListNode(0);
        ListNode smallTail = smallHead;
        // 大于等于部分的虚拟头结点
        ListNode bigHead = new ListNode(0);
        ListNode bigTail = bigHead;
        ListNode cur = head;
        // 将链表分为两部分
        while (cur != null) {
            if (cur.val < x) {
                smallTail.next = cur;
                smallTail = cur;
            } else {
                bigTail.next = cur;
                bigTail = cur;
            }
            cur = cur.next;
        }
        // 将两链表和并
        smallTail.next = bigHead.next;
        // 需要将最后一个节点的next置为null，防止形成环而报错
        // 如下，若不将bigTail.next == null，则会形成环
        // head = 1->4->3->2->5->2, x = 3
        // 1->2->2->4->3->5
        //       ^        |
        //       | <——————
        bigTail.next = null; 
        
        return smallHead.next;
    }
}

