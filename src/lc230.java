import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=230 lang=java
 *
 * [230] 二叉搜索树中第K小的元素
 * 
 * 题目: 查找给定二叉搜索树中第k个最小的元素
 *
 * 难度: medium
 * 
 * 思路: 二叉搜索树的中序遍历序列是升序的, 所以中序遍历二叉树找到第k个元素即可
 *      1. 递归
 *      2. 迭代
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
    private int cnt = 0;
    private int val = -1;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return val;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) return;
        dfs(root.left, k);
        cnt++;
        if (cnt == k) {
            val = root.val;
            return;
        }
        dfs(root.right, k);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
     */
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int count = 0;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            count++;
            if (count == k) {
                return cur.val;
            }
            cur = cur.right;
        }

        return -1;
    }
}

