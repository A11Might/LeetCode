import java.util.ArrayDeque;
import java.util.Deque;
/*
 * @lc app=leetcode.cn id=445 lang=java
 *
 * [445] 两数相加 II
 *
 * 题目: 将两个非空链表表示的非负整数相加, 返回一个新的链表, 链表的每个节点表示一位数字
 *      (链表节点是从高位到低位, 不能修改原链表)
 *
 * 难度: medium
 *
 * 思路: 使用栈逆序链表节点值, 再使用变量来跟踪进位, 并从包含最低有效位的栈顶开始模拟逐位
 *      相加的过程, 并且根据结果构建链表
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
     * 时间复杂度: O(n + m)
     * 空间复杂度: O(n + m)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = buildStack(l1);
        Deque<Integer> stack2 = buildStack(l2);
        int carry = 0; // 当前两位置和的进位
        // 使用哨兵结点dummyHead
        // dummy -> tail
        //        ^
        //        |  方便在这里插入高位的节点
        ListNode dummy = new ListNode(-1);
        // 分别从两个栈中从低位到高位弹出数字求和(注意carry)
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = a + b + carry;
            carry = sum / 10;
            ListNode cur = new ListNode(sum % 10);
            cur.next = dummy.next;
            dummy.next = cur;
        }

        return dummy.next;
    }

    // 将从高位到低位的链表, 逆序为低位到高位
    private Deque<Integer> buildStack(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        return stack;
    }
}