import java.util.ArrayDeque;
import java.util.Queue;
/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 * 
 * 题目：给定一个二叉树，找出其最小深度。
 *      （最小深度是从根结点到最近叶子结点的最短路径上的节点数量）
 *
 * 难度：easy
 * 
 * 思路：1. 树型DP，如果左右子树都不为空，那么树的最小深度等于左右子树的最小值加上1；
 *                如果左右子树有一个为空，那么树的深度等于非空子树的值加上1。(因为求的是叶节点的最小深度，如果子树为空就不能考虑这半边了)
 *      2. 按层遍历树每层的节点，若当前节点为叶结点，则找到最小深度。
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
     * 空间复杂度：O(n) (n 为树的高度即递归栈的深度)
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) (n 为树的高度)
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 当前节点为叶结点，到达最短深度所在层。
                if (cur.left == null && cur.right == null) return level;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }

        return -1;
    }
}

