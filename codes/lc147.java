/**
 * @author 胡启航
 * @date 2019/9/19 - 14:32
 *
 * 题目：对链表进行插入排序
 *
 * 难度：medium
 *
 * 思路：插入排序，使用dummyHead做有序部分的头节点
 *               遍历无序部分，依次将节点插入有序部分的适当位置
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 有序部分链表的头节点
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        // 遍历原链表
        while (head != null) {
            // 在有序链表中找到当前节点的合适位置
            while (tail.next != null) {
                if (tail.next.val < head.val) {
                    tail = tail.next;
                } else {
                    break;
                }
            }
            // 在将当前节点插入有序链表时
            // 先记录当前节点并先将其移动到下一个位置
            // (防止将当前节点插入有序链表，导致无序链表断链，无法完成遍历)
            ListNode cur = head;
            head = head.next;
            ListNode succ = tail.next;
            tail.next = cur;
            tail.next.next = succ;
            tail = dummyHead;
        }

        return dummyHead.next;
    }
}
