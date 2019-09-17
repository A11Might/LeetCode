/**
 * @author 胡启航
 * @date 2019/9/17 - 9:52
 *
 * [82] 删除排序链表中的重复元素 II
 *
 * 题目：删除给定链表中所有重复数字的节点(不是去重)
 *
 * 思路：使用虚拟头节点，将删除头节点的特殊情况一般化
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
    public ListNode deleteDuplicates(ListNode head) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            // 当前元素为重复元素时，删除所有连续的重复元素
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
            // 当前元素不是重复元素时，判断下一个元素
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dummyHead.next;
    }
}
