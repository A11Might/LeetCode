import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 * 
 * 题目：返回一个二叉树的中序遍历
 * 
 * 思路：1、递归
 *       2、迭代
 *       3、Morris遍历
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
    public List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) {
            return res;
        }
        return process(root);
    }

    private List<Integer> process(TreeNode node) {
        if (node == null) {
            return null;
        }
        process(node.left);
        res.add(node.val);
        process(node.right);
        return res;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    // morris中序遍历，第二次遇到当前节点时打印
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    res.add(cur.val); // <---
                    cur = cur.right;
                }
            } else {
                res.add(cur.val); // <---
                cur = cur.right;
            }
        }
        return res;
    }
}

