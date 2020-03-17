/*
 * @lc app=leetcode.cn id=543 lang=java
 *
 * [543] 二叉树的直径
 *
 * 题目：计算给定二叉树的直径长度。
 *      (一棵二叉树的直径长度是任意两个结点路径长度中的最大值，这条路径可能穿过根结点。)
 *
 * 难度：easy
 *
 * 思路：树型DP，定义 dfs 返回值为 {height, diameter}，height 为当前树的高度，diameter 为当前树的直径。
 *             二叉树的直径长度为左子树的直径长度，右子树的直径长度和经过当前根结点的直径长度三者中的最大值。
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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] ret = dfs(root);
        return ret[1];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[] {0, 0};
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int height = Math.max(left[0], right[0]) + 1;
        int diameter = Math.max(left[0] + right[0], Math.max(left[1], right[1]));
        return new int[] {height, diameter};
    }
}