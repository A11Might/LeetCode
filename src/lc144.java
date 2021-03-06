import java.util.*;

/*
 * @lc app=leetcode.cn id=144 lang=java
 *
 * [144] 二叉树的前序遍历
 * 
 * 题目：返回二叉树的前序遍历
 *
 * 难度：medium
 * 
 * 思路：1. 递归
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) (h 为二叉树的高度)
     */
    private List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        res.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) (h 为二叉树的高度)
     */
    public List<Integer> preorderTraversal2_1(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }

        return ans;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) (h 为二叉树的高度)
     */
    public List<Integer> preorderTraversal2_2(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stk = new ArrayDeque<>();
        var cur = root;
        while (cur != null || !stk.isEmpty()) {
            while (cur != null) {
                ans.add(cur.val);
                stk.push(cur);
                cur = cur.left;
            }
            cur = stk.pop().right;
        }

        return ans;
    }

    /**
     * Morris前序遍历, 在第一次遇到当前节点时打印
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) (h 为二叉树的高度)
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (mostRight != null) {
                // 注意当前节点的最右结点可能指向当前结点自己, 若指向自己则停止循环防止死循环
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    res.add(cur.val); // <--- 第一次遍历当前结点
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                // 当前节点没有左子树时, 相当于一下遍历当前结点两次(包含第一次和第二次)
                res.add(cur.val); // <--- 第一次遍历当前结点
                cur = cur.right;
            }
        }
        return res;
    }
}

