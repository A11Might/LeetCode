import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=230 lang=java
 *
 * [230] 二叉搜索树中第K小的元素
 * 
 * 题目：查找二叉搜索树的第k个小的元素
 *
 * 难度：medium
 * 
 * 思路：二叉搜索树的中序遍历是升序，中序遍历二叉树找到第k个元素即可
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
    public int kthSmallest1(TreeNode root, int k) {
        // 递归中的k为同一个k
        int[] K = {k};

        return dfs(root, K).val;
    }

    private TreeNode dfs(TreeNode root, int[] K) {
        if (root == null) {
            return null;
        }
        TreeNode ans = dfs(root.left, K);
        // 需要将ans = dfs(root.right, K)也括起来
        // 防止k[0] == 1返回至TreeNode ans = dfs(root.left, K)后，再将ans置为null
        if (ans == null) {
            if (K[0] == 1) {
                return root;
            }
            // 每遍历一个节点，都消耗一个k
            K[0]--;
            ans = dfs(root.right, K);
        }

        return ans;
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

