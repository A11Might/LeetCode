/*
 * @lc app=leetcode.cn id=114 lang=java
 *
 * [114] 二叉树展开为链表
 * 
 * 题意：给定一个二叉树，原地将它展开为链表
 * 
 * 思路：遍历整棵树，将每个节点的左子树作为节点的右子树接入树中，原右子树变为新右子树的右子树
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
    // 展链操作会改变树的结构，在还没操作节点的左右子树前，不能破坏该节点的左右子树指向
    // 采用后序遍历比较方便
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = temp;
    }
}

