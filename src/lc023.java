import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
 *
 * 题目：合并k个排序链表，返回合并后的排序链表
 *
 * 难度：hard
 *
 * 思路：将各个链表的首节点放入小根堆，
 *       弹出堆顶元素即为当前各个链表中的最小值，将弹出节点的后继节点再次放入小根堆，再弹出栈顶
 *       直至小根堆为空，返回头结点
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 1) { // 实例[]
            return null;
        }
        if (lists.length == 1) { // 只有一个链表时，直接返回lists[0]
            return lists[0];
        }
        ListNode dummyHead = new ListNode(0); // 哨兵节点，排序后的链表挂在其后面
        ListNode tail = dummyHead; // 已排序链表的最后一个元素
        // 小根堆，按val值从小到大排
        PriorityQueue<ListNode> smallHeap = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode head : lists) {
            if (head != null) { // 实例[[1], [], [3]]或[[],[]]
                smallHeap.add(head);
            }
        }
        while (!smallHeap.isEmpty()) {
            ListNode cur = smallHeap.poll();
            tail.next = cur;
            tail = cur;
            if (cur.next != null) {
                smallHeap.add(cur.next); // cur.next加入栈中，即比较剩下的节点
            }
        }

        return dummyHead.next;
    }
}