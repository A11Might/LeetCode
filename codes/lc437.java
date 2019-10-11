/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 *
 * 题目：找出给定二叉树中路径和等于给定数字的路径总数
 *      (路径不需要从根节点开始，也不需要在叶子节点结束，但路径方向必须向下)
 *
 * 难度：easy
 *
 * 思路：双重递归，首先先序递归遍历每个节点，再以每个节点作为起始点递归寻找满足条件的路径
 *      注：与之前写法(以形参形式保存结果)不同(112,257,113,129)，以返回只得形式保存结果
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
    // 在以root为根节点的二叉树中，寻找和为sum的路径
    // 返回这样路径的个数
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 以root为起点的所有路径和为sum的路径数
        int res = dfs(root, sum);
        // 以root.left为根节点的子树中，路径和为sum的路径数
        res += pathSum(root.left, sum);
        // 以root.right为根节点的子树中，路径和为sum的路径数
        res += pathSum(root.right, sum);

        return res;
    }

    // 在以root为根节点的二叉树中，寻找包含root并且路径和为curSum的路径
    // 返回这样的路径个数
    private int dfs(TreeNode root, int curSum) {
        if (root == null) {
            return 0;
        }
        curSum -= root.val;
        int res = 0;
        // 找到满足要求的一条路径
        if (curSum == 0) {
            res++;
        }
        // 未找到满足要求的路径继续递归
        res += dfs(root.left, curSum);
        res += dfs(root.right, curSum);

        return res;
    }
}

