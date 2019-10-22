/*
 * @lc app=leetcode.cn id=543 lang=java
 *
 * [543] 二叉树的直径
 * 
 * 二叉树的直径为根节点左子树的最大深度和右子树的最大深度之和
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
    private int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        res = Math.max(res, left + right); // 左子树与右子树深度之和即为树的直径
        return Math.max(left, right) + 1; // 节点的深度为，该节点左子树的最大深度和右子树的最大深度的较大值加一
    }
}

