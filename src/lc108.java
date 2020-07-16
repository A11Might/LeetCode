/*
 * @lc app=leetcode.cn id=108 lang=java
 *
 * [108] 将有序数组转换为二叉搜索树
 *
 * 题目: 将给定升序的数组转化为一棵高度平衡的二叉搜素树
 *
 * 难度: easy
 *
 * 思路: 递归，分而治之：f(n) = f(n / 2) + f(1) + f(n / 2)
 *      给定数组中的中间元素将会作为二叉搜索树的根, 该点左侧的所有元素递归的去构造左子树, 同理右侧的元素构造右子树.
 *      这必然能够保证最后构造出的二叉搜索树是平衡的
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
     * 空间复杂度: O(logn) (递归栈的深度为 logn)
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        // 左右等分数组，将中间元素作为子树根节点，然后递归的建立左右子树
        int mid = start + ((end - start) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, start, mid - 1);
        root.right = buildTree(nums, mid + 1, end);
        return root;
    }
}

