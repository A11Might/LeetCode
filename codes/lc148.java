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
        // 将链表一分为二，分别递归排序
        ListNode fast = node, slow = node, slowPre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slowPre = slow;
            slow = slow.next;
        }
        slowPre.next = null;
        ListNode left = mergeSort(node);
        ListNode right = mergeSort(slow);
        // 将排序后的两链表合并
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // 使用dummyhead，储存排序后的链表
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                tail = left;
                left = left.next;
            } else {
                tail.next = right;
                tail = right;
                right = right.next;
            }
        }
        // 剩下部分本就是有序链表，只需要与已排好序的链表连接一下即可
        if (left == null) {
            tail.next = right;
        } else {
            tail.next = left;
        }

        return dummyHead.next;
    }

    public:
    ListNode* sortList(ListNode* head) {
        ListNode dummyHead(0);
        dummyHead.next = head;
        auto p = head;
        int length = 0;
        while (p) {
            ++length;
            p = p->next;
        }
        
        for (int size = 1; size < length; size <<= 1) {
            auto cur = dummyHead.next;
            auto tail = &dummyHead;
            
            while (cur) {
                auto left = cur;
                auto right = cut(left, size); // left->@->@ right->@->@->@...
                cur = cut(right, size); // left->@->@ right->@->@  cur->@->...
                
                tail->next = merge(left, right);
                while (tail->next) {
                    tail = tail->next;
                }
            }
        }
        return dummyHead.next;
    }
    
    ListNode* cut(ListNode* head, int n) {
        auto p = head;
        while (--n && p) {
            p = p->next;
        }
        
        if (!p) return nullptr;
        
        auto next = p->next;
        p->next = nullptr;
        return next;
    }
    
    ListNode* merge(ListNode* l1, ListNode* l2) {
        ListNode dummyHead(0);
        auto p = &dummyHead;
        while (l1 && l2) {
            if (l1->val < l2->val) {
                p->next = l1;
                p = l1;
                l1 = l1->next;       
            } else {
                p->next = l2;
                p = l2;
                l2 = l2->next;
            }
        }
        p->next = l1 ? l1 : l2;
        return dummyHead.next;
    }
}

