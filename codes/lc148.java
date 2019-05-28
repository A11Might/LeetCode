/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 * 
 * 1、链表的归并排序：递归排序三部曲：1，快慢指针找中点；2，递归调用mergeSort，3，合并两个链表
 * 2、bottom-to-up：热评三https://leetcode-cn.com/problems/sort-list/comments/
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
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode fast = node, slow = node, slowPre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slowPre = slow;
            slow = slow.next;
        }
        slowPre.next = null;
        ListNode left = mergeSort(node);
        ListNode right = mergeSort(slow);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(0);
        ListNode end = dummyHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                end.next = left;
                end = left;
                left = left.next;
            } else {
                end.next = right;
                end = right;
                right = right.next;
            }
        }
        // 剩下部分本就是有序链表，只需要与已排好序的链表连接一下即可
        if (left == null) {
            end.next = right;
        } else {
            end.next = left;
        }
        return dummyHead.next;
    }
}

