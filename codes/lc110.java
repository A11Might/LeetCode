/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [110] 平衡二叉树
 * 
 * 题目：判断二叉树是否高度平衡
 * 
 * 思路：树型DP
 *     情况：
 *      a、左子树不平衡或者右子树不平衡
 *      b、左子树平衡并且右子树平衡，但高度差 > 1
 *      c、左子树平衡并且右子树平衡，但高度差 <= 1
 *     信息：
 *      a、左子树是否平衡及其高度
 *      b、右子树是否平衡及其高度
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
    public boolean isBalanced(TreeNode root) {
        return process(root).isB;
    }

    private ReturnData process(TreeNode node) {
        // 第三步，定义递归基
        if (node == null) {
            return new ReturnData(true, 0);
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        ReturnData left = process(node.left); // 左子树的信息
        ReturnData right = process(node.right); // 右子树的信息
        // 左子树不平衡或者右子树不平衡
        if (!left.isB || !right.isB) {
            return new ReturnData(false, 0);
        }
        // 左子树平衡并且右子树平衡，但高度差 > 1
        if (Math.abs(left.h - right.h) > 1) {
            return new ReturnData(false, 0);
        }
        // 第二步将黑盒实现
        // 左子树平衡并且右子树平衡，但高度差 <= 1
        return new ReturnData(true, Math.max(left.h, right.h) + 1);

    }

    // 定制返回值
    class ReturnData {
        public boolean isB;
        public int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }
}

