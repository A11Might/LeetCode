/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [328] 奇偶链表
 * 
 * 题目: 将给定链表的所有奇数节点和偶数节点分别排在一起
 *      (奇偶指的是节点编号, 保持奇数节点和偶数节点的相对顺序)
 *
 * 难度: medium
 * 
 * 思路: 类似快排的partition, 使用虚拟节点将链表分为奇数节点和偶数节点两个链表
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        // 奇数节点部分的虚拟头节点和偶数节点部分的虚拟头节点
        ListNode oddDummy = new ListNode(0), evenDummy = new ListNode(0);
        ListNode oddTail = oddDummy, evenTail = evenDummy;
        // 通过flag标识, 依次先后将遍历的节点放入奇偶两个链表
        boolean flag = true;
        while (head != null) {
            if (flag) {
                oddTail.next = head;
                oddTail = head;
            } else {
                evenTail.next = head;
                evenTail = head;
            }
            head = head.next;
            flag = !flag;
        }
        // 将两链表和并
        oddTail.next = evenDummy.next;
        // 关键:
        // 需要将合并后的链表的最后一个节点的next指针置为null, 来防止形成环而报错
        // 最后一个节点可能连在奇数节点上, 不置为null可能会形成环
        evenTail.next = null;
        
        return oddDummy.next;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return head;
        ListNode odd = head, even = head.next, evenHead = even;
        // 奇数位置节点连在奇数位置后, 偶数位置节点连在偶数位置后
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        // 连接奇偶两链表
        odd.next = evenHead;

        return head;
    }
}

