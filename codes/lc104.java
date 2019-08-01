/*
 * @lc app=leetcode.cn id=104 lang=java
 *
 * [104] 二叉树的最大深度
 * 
 * 题目：求二叉树的最大深度
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
        return process(root);
    }

    private int process(TreeNode node) {
        // 第三步，定义递归基
        if (node == null) {
            return 0;
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        int left = process(node.left); // 左子树的信息
        int right = process(node.right); // 右子树的信息
        // 第二步将黑盒实现
        int maxD = Math.max(left, right) + 1;
        return maxD;
    }
}

