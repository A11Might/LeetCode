/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 *
 * 题目: 找出给定二叉树中路径和等于给定数字的路径总数
 *      (路径不需要从根节点开始, 也不需要在叶子节点结束, 但路径方向必须向下)
 *
 * 难度: easy
 *
 * 思路: 遍历给定二叉树的每个节点, 寻找以当前节点为起始的路径和为sum的路径数(不需要在叶子节点结束, 但必须向下), 求和即可
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
    // 在以当前结点为根结点的二叉树中, 寻找和为sum的所有路径(路径不需要从根节点开始, 也不需要在叶子节点结束, 但路径方向必须向下), 并返回路径数
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // 在以当前节点为根节点的二叉树中, 寻找以当前节点开始且路径和为sum的路径(路径不用到达根结点, 但必须向下), 并返回路径数
    private int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        int ret = 0;
        // 找到满足要求的路径时, 继续向下寻找(因为节点值有负数)
        if (sum == root.val) ret++;
        sum -= root.val;
        ret += dfs(root.left, sum) + dfs(root.right, sum);

        return ret;
    }
}