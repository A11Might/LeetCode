import java.util.ArrayDeque;
import java.util.Deque;
/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 * 
 * 题目: 给定一个二叉树, 找出其最小深度
 *      (最小深度是从根结点到最近叶子结点的最短路径上的节点数量)
 *
 * 难度: easy
 * 
 * 思路: 1. 递归(树型dp), f(root) = min(f(root.left), f(root.right)) + 1
 *      2. 迭代, 按层遍历树每层的节点, 当当前节点为叶结点时, 找到最小深度
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
    public int minDepth(TreeNode root) {
        return dfs(root);
    }

    // 以root为根结点的树的最小深度
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 与二叉树的最大深度(lc104)不同的是, 需要注意当前路径是否到达叶子结点
        // 最大深度选择max得到的一定是到达叶子结点的路径, 而最小深度选择min得到的可能不是到达叶子结点的路径
        // 如下:
        //      3
        //        |
        //        4
        // 最短路径为2, 不是1
        // 当其中一个不是到达叶子结点的路径时
        if (left == 0 || right == 0) return left + right + 1;
        // 当两条都是到达叶子结点的路径时
        return Math.min(left, right) + 1;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0; // 当前节点所在层数
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size(); // 当前层所含节点数
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 当前节点为叶结点，到达最短深度所在层
                if (cur.left == null && cur.right == null) return level;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }

        return -1;
    }
}

