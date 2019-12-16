/**
 * @author qhhu
 * @date 2019/12/16 - 14:46
 *
 * [1290] 二进制链表转整数
 *
 * 题目: 给定一个单链表, 链表的每个节点为0或1, 表示一个整数数字的二进制, 返回该链表表示的数字的十进制值
 *
 * 难度: easy
 *
 * 思路: 从高位到低位, value = value * 2 + curNum
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
    public int getDecimalValue(ListNode head) {
        int ans = 0;

        while (head != null) {
            ans = ans * 2 + head.val;
            head = head.next;
        }

        return ans;
    }
}
