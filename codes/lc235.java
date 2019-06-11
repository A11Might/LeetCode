/*
 * @lc app=leetcode.cn id=235 lang=java
 *
 * [235] 二叉搜索树的最近公共祖先
 * 
 * 题意：在二叉搜索树中找到两个指定节点的最近公共祖先
 * 
 * 思路：利用二叉搜索树节点大小的特性
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q);
    }

    private TreeNode process(TreeNode node ,TreeNode p, TreeNode q) {
        // p和q在node两侧
        if ((node.val - p.val) * (node.val - q.val) <= 0) {
            return node;
        // p和q在node左侧
        } else if (p.val < node.val && q.val < node.val) {
            return process(node.left, p, q);
        // p和q在node右侧
        } else {
            return process(node.right, p, q);
        }
    }
}

