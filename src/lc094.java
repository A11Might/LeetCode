import java.util.*;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 * 
 * 题目: 返回一个二叉树的中序遍历
 *
 * 难度: medium
 * 
 * 思路: 1. 递归
 *      2. 迭代
 *      3. Morris遍历, 将当前节点记为cur
 *                    a. 如果cur无左孩子, cur向右移动
 *                    b. 如果cur有左孩子, 找到cur左孩子的最右节点, 记为mostRight
 *                       i.  如果mostRight.right指针指向null, 让其指向cur, cur向左移动
 *                       ii. 如果mostRight.right指针指向cur, 让其指回null, cur向右移动
 *                    c. 如果cur为空, morris遍历结束
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
    private List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        res.add(node.val);
        dfs(node.right);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }

        return ans;
    }

    /**
     * morris中序遍历, 第二次遇到当前节点时打印
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    res.add(cur.val); // <--- 第二次遍历当前结点
                    cur = cur.right;
                }
            } else {
                // 当前节点没有左子树时, 相当于一下遍历当前结点两次(包含第一次和第二次)
                res.add(cur.val); // <--- 第二次遍历当前结点
                cur = cur.right;
            }
        }
        return res;
    }
}

