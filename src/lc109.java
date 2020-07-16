/**
 * @author qhhu
 * @date 2020/2/9 - 9:14
 *
 * [109] 有序链表转换二叉搜索树
 *
 * 题目: 给定一个元素按升序排序的单链表, 将其转换为高度平衡的二叉搜索树
 *
 * 难度: medium
 *
 * 思路: 递归，分而治之：f(n) = f(n / 2) + f(1) + f(n / 2)
 *      给定列表中的中间元素将会作为二叉搜索树的根, 该点左侧的所有元素递归的去构造左子树, 同理右侧的元素构造右子树.
 *      这必然能够保证最后构造出的二叉搜索树是平衡的
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn) (树的高度为 logn 即递归栈的深度, 并且每次递归为了找到中间元素需要遍历链表, 链表长度为 n)
     * 空间复杂度: O(logn) (递归栈的深度为 logn)
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode fast = head.next.next, slow = head;
        ListNode pre = slow; // pre 为链表中间结点的前一个结点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        ListNode mid = pre.next;
        pre.next = null;
        // 将链表的前半部分和后半部分转化为平衡树，然后与根节点连接起来
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }
}