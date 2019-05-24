/*
 * @lc app=leetcode.cn id=160 lang=java
 *
 * [160] 相交链表
 * 
 * 1、修正两链表起始位置后，再依次遍历找交点
 * 2、将一条链表成环，以另一条链表头出发成入环的第一个节点
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */
public class Solution {
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode curA = headA;
        ListNode curB = headB;
        int count = 0;
        while (curA.next != null) {
            count++;
            curA = curA.next;
        }
        while (curB.next != null) {
            count--;
            curB = curB.next;
        }
        if (curA != curB) {
            return null;
        }
        if (count < 0) {
            count = Math.abs(count);
            while (count != 0) {
                headB = headB.next;
                count--;
            }
        } else {
            while (count != 0) {
                headA = headA.next;
                count--;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    // 原理同一
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差) 两个指针等于移动了相同的距离,
         * 有交点就返回, 无交点就是各走了两条指针的长度
         **/
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode last = headB;
        while (last.next != null) {
            last = last.next;
        }
        last.next = headB;

        ListNode fast = headA;
        ListNode slow = headA;

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
        last.next = null;
        return null;
    }

}
