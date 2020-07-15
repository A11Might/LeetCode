import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=112 lang=java
 *
 * [112] 路径总和
 *
 * 题目: 判断给定二叉树中是否存在一个根节点到叶子结点的路径, 其上所有节点和等于目标值
 *
 * 难度: easy
 * 
 * 思路: 1. 递归：
 *         如果当前节点是叶子, 检查 sum 值是否为 node.val
 *         如果当前节点不是叶子, 对它的孩子节点递归调用函数, 判断其孩子节点是否存在 sum - node.val 的路径
 *
 *      2. 迭代：
 *         使用先序遍历找到所有路径，判断路径和是否为目标和
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
     * 空间复杂度: O(n) (n 为树的高度即递归栈的深度)
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false; // 实例[] 0 -> false
        if (root.left == null && root.right == null) return sum == root.val; // 到达叶结点
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n 为树的高度)
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair(root, root.val));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode curNode = pair.getKey();
            int curPath = pair.getValue();
            // 到达叶结点时, 判断当前路径总和是否为sum, 若是则直接返回, 若不是则需继续遍历
            if (curNode.left == null && curNode.right == null
                    && curPath == sum) return true;
            if (curNode.right != null) {
                stack.push(new Pair(curNode.right, curPath + curNode.right.val));
            }
            if (curNode.left != null) {
                stack.push(new Pair(curNode.left, curPath + curNode.left.val));
            }
        }

        return false;
    }
}

