/*
 * @lc app=leetcode.cn id=543 lang=java
 *
 * [543] 二叉树的直径
 *
 * 题目: 计算给定二叉树的直径长度
 *      (一棵二叉树的直径长度是任意两个结点路径长度中的最大值, 这条路径可能穿过根结点)
 *
 * 难度: easy
 *
 * 思路: 树型dp, 二叉树的直径为所有结点左子树的最大深度和右子树的最大深度之和中的最大值
 *
 *                 a
 *           b          c
 *       d       e
 *     f   g   l    m
 *    n o p q r s  t u
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
    private int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        diameter = Math.max(diameter, left + right); // 左子树与右子树深度之和即为树的直径
        return Math.max(left, right) + 1; // 节点的深度为, 该节点左子树的最大深度和右子树的最大深度的较大值加一
    }
}

