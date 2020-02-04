/**
 * @author qhhu
 * @date 2020/2/4 - 8:48
 *
 * [725] 分隔链表
 *
 * 题目: 将给定链表分成k个连续的部分, 每部分长度应该尽可能地相等(任意两部分长度差距不超过1, 即某些部分可能为null),
 *      这k个部分按照链表中出现的顺序进行输出, 并且排在前面的部分长度大于后面的, 返回符合要求的链表列表
 *
 * 难度: medium
 *
 * 思路: 将链表分为k个部分, 依次处理第1部分到第k部分
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
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0; // 链表长度
        ListNode cur = root;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        ListNode[] ret = new ListNode[k];
        int mod = len % k; // 平均分为k个部分后多余的节点数, 将其分摊到前mod个部分中
        int size = len / k; // 平均分为k个部分, 每个部分初始的节点数
        cur = root;
        // 依次处理第1部分到第k部分
        // 当当前节点为空时, 后续部分都为空, 不用再处理
        for (int i = 0; cur != null && i < k; i++) {
            ret[i] = cur; // 当前部分开始节点
            // 将平均分为k个部分后多余的节点数, 分摊到前mod个部分中
            int curSize = size + (mod-- > 0 ? 1 : 0);
            while (--curSize > 0) {
                cur = cur.next;
            }
            // 切断前一部分和后一部分的连接
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }

        return ret;
    }
}
