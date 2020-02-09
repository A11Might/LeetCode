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
 * 思路: 同lc257二叉树的所有路径
 *      1. 递归, 遍历整棵树:
 *              如果当前节点是叶子, 检查 sum 值是否为 node.val, 也就是是否找到了给定的目标和
 *              如果当前节点不是叶子, 对它的所有孩子节点, 递归调用 dfs 函数, 其中 sum 值减去当前节点的权值
 *
 *      2. 迭代, dfs: 利用深度优先策略访问每个节点, 同时更新当前路径和
 *      (深度优先搜索在除了最坏情况下都比广度优先搜索更快(对于本题bfs需要遍历所有节点)
 *      最坏情况是指满足目标和的 root->leaf 路径是最后被考虑的, 这种情况下深度优先搜索和广度优先搜索代价是相同的)
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
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root == null) return false; // 实例[] 0 -> false
        // 到达叶结点
        if (root.left == null && root.right == null) return root.val == sum;
        sum -= root.val;
        return dfs(root.left, sum) || dfs(root.right, sum);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
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

