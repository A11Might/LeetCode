/*
 * @lc app=leetcode.cn id=108 lang=java
 *
 * [108] 将有序数组转换为二叉搜索树
 *
 * 题目：将有序数组转化为二叉搜素树
 *
 * 难度：easy
 *
 * 思路：分治，f(n) = f(n / 2) + f(1) + f(n / 2)
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 左右等分建立左右子树，中间节点作为子树根节点，递归该过程
        int mid = start + ((end - start) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, start, mid - 1);
        root.right = buildTree(nums, mid + 1, end);

        return root;
    }
}

