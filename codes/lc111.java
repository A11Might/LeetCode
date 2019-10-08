/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 * 
 * 题目：给定一个二叉树，找出其最小深度
 *
 * 难度：easy
 * 
 * 思路：1、递归，min(root.left, root.right) + 1
 *      2、bfs，按层遍历树每层的节点，当当前节点为叶结点时，找到最小深度
 */

import java.util.ArrayDeque;
import java.util.Deque;

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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 与二叉树的最大深度不同的是，需要注意当前路径是否到达叶子结点
        // 如下：
        //      3
        //        |
        //        4
        // 最短路径为2，不是1
        if (root.left == null && root.right == null) {
            return 1;
        }
        int res = Integer.MAX_VALUE;
        if (root.left != null) {
            res = Math.min(res, 1 + minDepth(root.left));
        }
        if (root.right != null) {
            res = Math.min(res, 1 + minDepth(root.right));
        }

        return res;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0; // 当前节点所在层数
        while (!queue.isEmpty()) {
            int levelNum = queue.size(); // 当前层所含节点数
            level++;
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = queue.poll();
                // 当前节点为叶结点，到达最短深度所在层
                if (cur.left == null && cur.right == null) {
                    return level;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        throw new IllegalStateException("no solution");
    }
}

