/**
 * @author qhhu
 * @date 2019/10/10 - 12:19
 *
 * [404] 左叶子之和
 *
 * 题目: 计算给定二叉树的所有左叶子之和
 *
 * 难度: easy
 *
 * 思路: 递归(减而治之)
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
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root);
    }

    // 以root为根结点的树的所有左叶子结点的和
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        // 当前节点的左子节点是叶子结点
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + dfs(root.right);
        }
        // 当前节点的左子节点不是是叶结点
        return dfs(root.left) + dfs(root.right);
    }
}
