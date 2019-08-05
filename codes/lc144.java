import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=144 lang=java
 *
 * [144] 二叉树的前序遍历
 * 
 * 题目：返回二叉树的前序遍历
 * 
 * 思路：1、递归
 *      2、迭代
 *      3、Morris遍历
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
    public List<Integer> res = new LinkedList<>();
    public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) {
            return res;
        }
        return process(root);
    }

    private List<Integer> process(TreeNode node) {
        if (node == null) {
            return null;
        }
        res.add(node.val);
        process(node.left);
        process(node.right);
        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    // Morris前序遍历，在第一次遇到当前节点时打印
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new LinkedList<>();
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    res.add(cur.val); // <---
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
                // 当前节点没有左子树时，相当于一下遍历当前节点两次(包含第一次和第二次)
            } else {
                res.add(cur.val); // <---
                cur = cur.right;
            }
        }
        return res;
    }
}

