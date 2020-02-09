import java.util.ArrayDeque;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=226 lang=java
 *
 * [226] 翻转二叉树
 *
 * 题目: 反转给定二叉树
 *
 * 难度: easy
 * 
 * 思路: 翻转每个子树的左右节点 1. 递归(减而治之)
 *                       2. 迭代
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
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) return null;
        // 一步到位(牛逼呀)
        // 反转当前节点的右子树后, 直接将其连在当前节点的左指针上
        // 反转当前节点的左子树后, 直接将其连在当前节点的右指针上
        TreeNode left = root.left;  // 后面的操作会改变left指针, 因此先保存下来
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 翻转当前节点的左右子节点
            TreeNode temp = cur.right;
            cur.right = cur.left;
            cur.left = temp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }

        return root;
    }
}
