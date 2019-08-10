import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=230 lang=java
 *
 * [230] 二叉搜索树中第K小的元素
 * 
 * 题目：查找二叉搜索树的第k个小的元素
 * 
 * 思路：二叉搜索树的中序遍历是升序，中序遍历二叉树找到第k个元素即可
 *      (剑指offer上的方法看不懂，难过)
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
    public TreeNode res = null;
    public int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return res.val;
    }

    private void dfs(TreeNode node, int k) {
        // shut down the unnecessary recursion
        // when count bigger than k
        // the recursion is unnecessray
        if (node == null || count > k) {
            return;
        }
        dfs(node.left, k);
        count++;
        if (count == k) {
            res = node;
        }
        dfs(node.right, k);
    }

    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int count = 0;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                count++;
                if (count == k) {
                    return cur.val;
                }
                cur = cur.right;
            }
        }
        return 0;
    }
}

