/*
 * @lc app=leetcode.cn id=104 lang=java
 *
 * [104] 二叉树的最大深度
 * 
 * 题目：求给定二叉树的最大深度
 *
 * 难度：easy
 * 
 * 思路：树型dp
 *      情况：a、最大深度来自左子树
 *            b、最大深度来自右子树
 *      信息：当前树的深度
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
    public int maxDepth(TreeNode root) {
        // 第三步，定义递归基
        if (root == null) {
            return 0;
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        // 第二步将黑盒实现
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
