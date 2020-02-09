import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=538 lang=java
 *
 * [538] 把二叉搜索树转换为累加树
 *
 * 题目: 将给定的二叉搜索树转化为累加树, 使得每个结点的值时原来的结点值加上所有大于它的结点值之和
 *
 * 难度: easy
 * 
 * 思路: 二叉搜索树的中序遍历序列是升序的, 将其倒过来即以右->中->左的顺序遍历二叉树则成为降序, 遍历的结点值从大到小,
 *      这时将前一个结点的值(该节点加上所有大于它的结点值之和的值)记录起来, 和当前结点相加即可得到当前结点的累加值
 *      (想到中序序列升序, 当前结点的值应为所有节点之和减去前一个节点的值(该节点加上所有大于它的结点值之和的值), 没想到从后往前, 还是菜)
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
    // 每层递归用的都是这个sum, 定义为全局变量(也可以在定义在dfs(TreeNode root, int[] sum)中)
    private int preSum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        root.val += preSum;
        preSum = root.val;
        dfs(root.left);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
     */
    public TreeNode convertBST2(TreeNode root) {
        int sum = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            cur.val += sum;
            sum = cur.val;
            cur = cur.left;
        }
        return root;
    }
}

