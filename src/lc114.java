/*
 * @lc app=leetcode.cn id=114 lang=java
 *
 * [114] 二叉树展开为链表
 * 
 * 题意：给定一个二叉树，原地将它展开为链表
 *
 * 难度：medium
 * 
 * 思路：树形DP，将左右子树分别转化成链表，然后与根节点拼接成一个大链表。
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) (递归栈的深度为树的高度 n)
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        // 将左右子树分别转化成链表
        flatten(root.left);
        flatten(root.right);
        // 与根节点拼接成一个大链表
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) root = root.right;
        root.right = right;
    }
}

