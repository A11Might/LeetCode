/*
 * @lc app=leetcode.cn id=160 lang=java
 *
 * [160] 相交链表
 *
 * 题目：返回两个单链表相交的起始节点
 *
 * 难度：easy
 * 
 * 思路：1. 修正两链表起始位置后，再依次遍历找交点
 *      2. 将一条链表成环，以另一条链表头出发成入环的第一个节点
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lenA = 0, lenB = 0;
        ListNode pointA = headA, pointB = headB;
        while (pointA != null || pointB != null) {
            if (pointA != null) {
                lenA++;
                pointA = pointA.next;
            }
            if (pointB != null) {
                lenB++;
                pointB = pointB.next;
            }
        }

        // 修正两链表起始位置后
        int offset = Math.abs(lenA - lenB);
        pointA = lenA > lenB ? headA : headB;
        pointB = lenA > lenB ? headB : headA;
        while (offset-- > 0) {
            pointA = pointA.next;
        }

        // 再依次遍历找交点
        while (pointA != null && pointB != null) {
            if (pointA == pointB) return pointA;
            pointA = pointA.next;
            pointB = pointB.next;
        }

        return null;
    }

    /**
     * 定义两个指针指向两个链表的头结点，开始遍历链表，当这两个指针到达链尾结点时令指针指向另一个链表的头结点，再次遍历链表
     * 当两指针指向相同的结点时，该结点为两链表的交点（在第一轮遍历中恰好抹除了长度差，当遍历到交点时两个指针移动了相同的距离）
     * 若两链表没有交点，则两指针分别遍历完两个链表，如下：
     *
     * 原链表为：
     * a -> b -> c
     *              -> d  -> g
     *      e -> f
     *
     * 算法为：
     *
     * a -> b -> c               -> e -> f
     *              -> d  -> g                 -> d  -> g
     *      e -> f              -> a -> b -> c
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     **/
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode p = headA, q = headB;
        // 第一轮遍历在 p 和 p 第一次到达尾部会移向另一链表的表头
        // 第二轮遍历在若 p 或 p 相交就返回交点，不相交最后就是 null == null
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // 让 B 链表成环
        ListNode last = headB;
        while (last.next != null) {
            last = last.next;
        }
        last.next = headB;

        // 使用快慢指针找到入环的第一个结点
        ListNode fast = headA, slow = headA;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = headA;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                last.next = null;
                return fast;
            }
        }

        // 将环恢复成B链表
        last.next = null;
        return null;
    }

}
