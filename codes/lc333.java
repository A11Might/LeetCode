/*
 * @lc app=leetcode.cn id=333 lang=java
 *
 * [333] 最大 BST 子树
 * 
 * 题目：给定一个二叉树的头节点，返回最大二叉搜索子树的大小
 * 
 * 思路：树型DP
 *      情况：
 *          a、最大二叉搜索子树来自head的左子树中
 *          b、最大二叉搜索子树来自head的右子树中
 *          c、左右子树都是二叉搜索子树，并且左子树的max < head < 右子树的max
 *      信息：
 *          a、向左搜索最大二叉搜索子树大小
 *          b、向右搜索最大二叉搜索子树大小        精简      1、搜索最大二叉搜索子树大小
 *          c、向左搜索最大二叉搜索子树的头节点   ======>    2、搜索最大二叉搜索子树的头节点
 *          d、向右搜索最大二叉搜索子树的头节点              3、搜索最大二叉搜索子树的最小值和最大值
 *          e、向左搜索最大二叉搜索子树的最大值
 *          f、向右搜索最大二叉搜索子树的最小值
 */
/**
 * Definition for a binary tree node. 
 * public class TreeNode { 
 *     int val; 
 *     TreeNode left; 
 *     TreeNode right; 
 *     TreeNode(int x) { val = x; } }
 */
class Solution {
    public int biggestSubBST(TreeNode root) {
        return process(root).size;
    }

    private ReturnData process(TreeNode node) {
        // 第三步，定义递归基
        if (node == null) {
            return new ReturnData(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        ReturnData left = process(node.left); // 左子树的信息
        ReturnData right = process(node.right); // 右子树的信息
        // 最大二叉搜索子树来自head的左子树中
        int case1 = left.size;
        // 最大二叉搜索子树来自head的右子树中
        int case2 = right.size;
        int case3 = 0;
        // 左右子树都是二叉搜索子树，并且左子树的max < head < 右子树的max
        if (left.head == node.left && right.head == node.right && left.max < node.val && right.min > node.val) {
            case3 = left.size + 1 + right.size;
        }
        int maxSize = Math.max(case1, Math.max(case2, case3));
        TreeNode maxNode = case1 > case2 ? left.head : right.head;
        if (maxSize == case3) {
            maxNode = node;
        }
        // 第二步将黑盒实现
        return new ReturnData(maxSize, maxNode, Math.max(left.max, Math.max(right.max, node.val)), // 包括三种情况，所以要比较
                Math.min(left.min, Math.min(right.min, node.val))); // 同上
    }

    // 定制返回值
    class ReturnData {
        public int size;
        public TreeNode head;
        public int max;
        public int min;

        public ReturnData(int size, TreeNode head, int max, int min) {
            this.size = size;
            this.head = head;
            this.max = max;
            this.min = min;
        }
    }
}
