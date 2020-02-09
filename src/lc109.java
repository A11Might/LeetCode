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
 * 思路: 同[108] 将有序数组转换为二叉搜索树, 分而治之, f(n) = f(n / 2) + f(1) + f(n / 2)
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
     * 时间复杂度: O(n * logn) (树的高度为logn即递归栈的深度, 并且每次递归为了找到中间元素需要遍历链表, 链表长度为n)
     * 空间复杂度: O(logn) (递归栈的深度为logn)
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        // 注意当只有一个结点时需要返回
        // 因为当只有一个结点时, preMid == head而mid == null, 若继续递归会出现空指针异常
        if (head.next == null) return new TreeNode(head.val);
        ListNode preMid = getListPreMid(head);
        ListNode mid = preMid.next;
        // 需要断链(用于将父问题转换为个子问题), 所以要求mid的前一个结点(即getListPreMid())
        preMid.next = null;
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    // 返回中间结点的前一个结点
    private ListNode getListPreMid(ListNode head) {
        ListNode fast = head, slow = head;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }

        return pre;
    }
}