/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 *
 * 题目：在 O(n * logn) 的时间复杂度和空间复杂度下，对链表进行排序
 *
 * 难度：medium
 *
 * 思路： 1. 归并排序
 *       2. 快速排序
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
     * 时间复杂度：O(n * logn)
     * 空间复杂度：O(logn)
     */
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head, slow = head;
        ListNode midPre = slow; // 链表中间节点的前一个节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            midPre = slow;
            slow = slow.next;
        }
        // 将链表等分成两个部分
        ListNode head2 = midPre.next;
        midPre.next = null;
        // 归并排序
        head = sortList1(head);
        head2 = sortList1(head2);
        return mergeList(head, head2);
    }

    private ListNode mergeList(ListNode p, ListNode q) {
        if (p == null) return q;
        if (q == null) return p;
        ListNode dummy = new ListNode(-1), tail = dummy;
        while (p != null || q != null) {
            int val1 = p == null ? Integer.MAX_VALUE : p.val;
            int val2 = q == null ? Integer.MAX_VALUE : q.val;
            if (val1 <= val2) {
                tail.next = p;
                p = p.next;
            } else {
                tail.next = q;
                q = q.next;
            }
            tail = tail.next;
        }
        return dummy.next;
    }

    /**
     * 时间复杂度：O(n * logn)
     * 空间复杂度：O(logn)
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        int x = head.val;
        // 将链表分为小于 x、等于 x、大于 x 三部分
        ListNode headL = new ListNode(-1), tailL = headL;
        ListNode headM = new ListNode(-1), tailM = headM;
        ListNode headR = new ListNode(-1), tailR = headR;
        while (head != null) {
            if (head.val < x) {
                tailL.next = head;
                tailL = head;
            } else if (head.val > x) {
                tailR.next = head;
                tailR = head;
            } else {
                tailM.next = head;
                tailM = head;
            }
            head = head.next;
        }
        // 将三个子链表排序
        tailL.next = tailM.next = tailR.next = null;
        headL.next = sortList2(headL.next);
        headR.next = sortList2(headR.next);
        // 合并成一个链表
        tailL = getTail(headL);
        tailM = getTail(headM);
        tailL.next = headM.next;
        tailM.next = headR.next;
        return headL.next;
    }

    private ListNode getTail(ListNode head) {
        if (head == null || head.next == null) return head;
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}