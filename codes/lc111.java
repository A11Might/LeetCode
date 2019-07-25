/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 * 
 * 题目：给定一个二叉树，找出其最小深度
 * 
 * 思路：1、递归，min(root.left, root.right) + 1
 *      2、bfs，遍历树每层的节点，当当前节点为叶结点时，找到最小深度
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
    public int minDepth1(TreeNode root) {
        // root == null
        if (root == null) {
            return 0;
        }
        // root.left == null & root.right == null
        // root.left == null & root.right != null
        if (root.left == null) {
            return minDepth(root.right) + 1;
        // root.left != null & root.right == null
        }else if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        // root.left != null & root.right != null
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0; // 层数
        while (!queue.isEmpty()) {
            int leveNum = queue.size(); // 当前层所含节点数
            for (int i = 0; i < leveNum; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) { // 当前节点为叶结点，到达最短深度所在层
                    return level + 1; // 之前的层数加上当前叶节点的一层
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            level++;
        }

        return level;
    }
}

