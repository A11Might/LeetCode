/**
 * @author 胡启航
 * @date 2019/9/19 - 14:32
 *
 * 题目：对链表进行插入排序
 *
 * 难度：medium
 *
 * 思路：插入排序，使用 dummyHead 做有序部分的头节点，遍历无序部分，依次将节点插入有序部分的适当位置
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 有序部分链表的头节点
        ListNode dummy = new ListNode(-1);
        for (var p = head; p != null;) {
            ListNode cur = dummy, succ = p.next;
            // 在有序链表中找到插入当前节点的合适位置
            while (cur.next != null && cur.next.val <= p.val) {
                cur = cur.next;
            }
            // 将当前节点插入有序部分链表中
            p.next = cur.next;
            cur.next = p;
            p = succ;
        }
        return dummy.next;
    }
}
