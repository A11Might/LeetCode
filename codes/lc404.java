/**
 * @author qhhu
 * @date 2019/10/10 - 12:19
 *
 * [404] 左叶子之和
 *
 * 题目：计算给定二叉树的所有左叶子之和
 *
 * 难度：easy
 *
 * 思路：递归
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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当前节点的左子节点是叶结点
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        // 当前节点的左子节点不是是叶结点
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}
