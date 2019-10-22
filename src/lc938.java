import java.util.Stack;

/*
 * @lc app=leetcode.cn id=938 lang=java
 *
 * [938] 二叉搜索树的范围和
 * 
 * 题目：值在[L, R]之间的二叉搜索树中所有节点值的和
 * 
 * 思路：dfs
 *      1、递归，递归遍历每个节点(优化后不是每个)，满足条件就就加入sum
 *      2、迭代，迭代遍历每个节点(优化后不是每个)，满足条件就就加入sum
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public int res = 0;

    public int rangeSumBST(TreeNode root, int L, int R) {
        return dfs(root, L, R);
    }

    private int dfs(TreeNode node, int L, int R) {
        if (node == null) {
            return 0;
        }
        if (L <= node.val && node.val <= R) {
            res += node.val;
        }
        if (L < node.val) {
            dfs(node.left, L, R);
        }
        if (node.val < R) {
            dfs(node.right, L, R);
        }
        return res;
    }

    public int rangeSumBST2(TreeNode root, int L, int R) {
        Stack<TreeNode> stack = new Stack<>();
        int res = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) { // 可能压入null节点，跳过空节点，防止空指针错误
                if (L <= cur.val && cur.val <= R) {
                    res += cur.val;
                }
                // 优化效率
                // 当cur.val > L时，cur.left才可能在[l, r]之间
                // 当cur.val < R时，cur.right才可能在[l, r]之间
                if (L < cur.val) {
                    stack.push(cur.left);
                }
                if (cur.val < R) {
                    stack.push(cur.right);
                }
            }
        }

        return res;
    }
}
