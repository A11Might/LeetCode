import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=100 lang=java
 *
 * [100] 相同的树
 * 
 * 题目：判断两个二叉树是否相同
 *
 * 难度：easy
 * 
 * 思路：同101对称二叉树
 *      1、递归，递归判断每个节点是否相同
 *      2、迭代，bfs判断每个节点是否相同
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p == null & q == null
        if (p == null && q == null) {
            return true;
        }
        // p == null & q != null 或者 p != null & q == null
        if (p == null || q == null) {
            return false;
        }
        // p != null & q != null
        if (p.val != q.val) {
            return false;
        }
        // 递归判断每个节点是否相同
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode cur1 = queue.poll();
            TreeNode cur2 = queue.poll();
            // 判断相同位置节点是否相等
            if (cur1 == null && cur2 == null) {
                continue;
            }
            if (cur1 == null || cur2 == null) {
                return false;
            }
            if (cur1.val != cur2.val) {
                return false;
            }
            // 将相同位置的节点，放在相邻的位置
            queue.add(cur1.left);
            queue.add(cur2.left);
            queue.add(cur1.right);
            queue.add(cur2.right);
        }
        return true;
    }
}
