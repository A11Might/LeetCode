import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=145 lang=java
 *
 * [145] 二叉树的后序遍历
 * 
 * 题目：返回二叉树的后序遍历
 * 
 * 思路：1、递归
 *      2、迭代，空间复杂度O(2N)
 *      3、迭代，空间复杂度O(N)
 *      4、Morris遍历
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
    public List<Integer> postorderTraversal1(TreeNode root) {
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
        process(node.right);
        res.add(node.val);
        return res;
    }

    // 用两个栈实现后序遍历（改造先序遍历）
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>(); // 弹出顺序中右左
        Deque<Integer> help = new LinkedList<>(); // 弹出顺序左右中
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            help.push(cur.val);
            // 栈中先压左，再压右，弹出顺序为先右后左
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!help.isEmpty()) {
            res.add(help.pop());
        }
        return res;
    }

    // 用一个栈实现后序遍历
    public List<Integer> postorderTraversal3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode cur = null;
        TreeNode visited = root;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left != null && visited != cur.left && visited != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && cur.right != visited) {
                stack.push(cur.right);
            } else {
                res.add(stack.pop().val);
                visited = cur;
            }
        }
        return res;
    }

    // 后序遍历，逆序打印真的第二次回到自己的节点左子树的右边界，函数退出前打印整棵树的右边界
    public List<Integer> ans = new LinkedList<>();
    public List<Integer> postorderTraversal4(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
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
                    printEdge(cur.left); // 先恢复左子树，再逆序存储左子树的右边界
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printEdge(root); // 逆序存储整棵树的右边界
        return ans;
    }

    // 逆序打印当前节点右边界
    private void printEdge(TreeNode node) {
        TreeNode tail = reverseEdge(node);
        TreeNode cur = tail;
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 反转当前节点右边界
    private TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode cur = from;
        TreeNode succ = null;
        while (cur != null) {
            succ = cur.right;
            cur.right = pre;
            pre = cur;
            cur = succ;
        }
        return pre;
    }
}

