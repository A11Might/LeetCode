/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [110] 平衡二叉树
 *
 * 题目: 判断二叉树是否高度平衡
 *
 * 难度: easy
 *
 * 思路: 树型DP, 情况: a. 左子树不平衡或者右子树不平衡
 *                  b. 左子树平衡并且右子树平衡, 但高度差 > 1
 *                  c. 左子树平衡并且右子树平衡, 但高度差 <= 1
 *             信息:
 *                   a. 左子树是否平衡及其高度
 *                   b. 右子树是否平衡及其高度
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
    public boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1;
    }

    // return int[]
    // int[0] is balanced or not(0 no; 1 yes)
    // int[1] tree's height
    private int[] dfs(TreeNode root) {
        // 第三步, 定义递归基
        if (root == null) return new int[] {1, 0};

        // 第一步, 把process当做黑盒
        // 讨论每种情况
        int[] lmsg = dfs(root.left); // 左子树的信息
        int[] rmsg = dfs(root.right); // 右子树的信息
        // 左子树不平衡或者右子树不平衡, 直接返回不平衡即可不用再考虑树高(每次递归遇到不平衡就直接返回了)
        if (lmsg[0] == 0 || rmsg[0] == 0) return new int[] {0, 0};
        // 左子树平衡并且右子树平衡, 但高度差 > 1
        if (Math.abs(lmsg[1] - rmsg[1]) > 1) return new int[] {0, 0};

        // 第二步将黑盒实现
        // 左子树平衡并且右子树平衡, 但高度差 <= 1
        return new int[] {1, 1 + Math.max(lmsg[1], rmsg[1])};
    }
}

