import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=226 lang=java
 *
 * [226] 翻转二叉树
 *
 * 题目：反转给定二叉树
 *
 * 难度：easy
 * 
 * 思路：翻转每个子树的左右节点
 *      1、递归
 *      2、迭代
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 翻转当前节点的左右子节点
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        // 翻转当前节点左右子树
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            swap(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return root;
    }

    private void swap(TreeNode node) { // 翻转当前节点的左右子节点
        TreeNode temp = node.right;
        node.right = node.left;
        node.left = temp;
    }
}
