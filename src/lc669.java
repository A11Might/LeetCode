/**
 * @author qhhu
 * @date 2020/2/8 - 9:19
 *
 * [669] 修剪二叉搜索树
 *
 * 题目: 给定一个二叉搜索树和边界[L, R], 修剪二叉搜索树使得其所有节点的值都在[L, R]中(R >= L), 返回修剪后的二叉搜索树的新根结点
 *      (可能需要修改树的根结点)
 *
 * 难度: easy
 *
 * 思路: 递归(分而治之), f(root) = root + f(root.left) + f(root.right)
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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        return dfs(root, L, R);
    }

    // 修剪该以root为根结点的二叉搜索树所有边界[L, R]外的节点
    private TreeNode dfs(TreeNode root, int L, int R) {
        if (root == null) return null;
        // 二叉搜索树中当前节点左侧所有结点值小于当前结点, 当前结点右侧所有值大于当前结点
        if (root.val > R) return dfs(root.left, L, R);
        if (root.val < L) return dfs(root.right, L, R);
        root.left = dfs(root.left, L, R);
        root.right = dfs(root.right, L, R);
        return root;
    }
}
