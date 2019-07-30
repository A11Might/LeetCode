import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=100 lang=java
 *
 * [100] 相同的树
 * 
 * 题目：判断两个二叉树是否相同
 * 
 * 思路：1、递归，递归判断每个节点是否相同
 *      2、迭代，bfs判断每个节点是否相同
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isSameTree1(TreeNode p, TreeNode q) {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (!check(p, q)) {
            return false;
        }
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.offer(p);
        queueQ.offer(q);
        while (!queueP.isEmpty()) {
            p = queueP.poll();
            q = queueQ.poll();
            if (!check(p, q)) { // 当前节点相同(可能都为null)
                return false;
            }
            if (p != null) { // 当p为null时，q也为null，跳过为空的情况(节点为空时无法check)
                // 当p、q节点的左子节点相同，加入队列中
                // 若不判断，则可能出现队列中加入空的情况(出现空指针错误)
                if (!check(p.left, q.left)) {
                    return false;
                }
                if (p.left != null) {
                    queueP.offer(p.left);
                    queueQ.offer(q.left);
                }
                // 当p、q节点的右子节点相同，加入队列中
                if (!check(p.right, q.right)) {
                    return false;
                }
                if (p.right != null) {
                    queueP.offer(p.right);
                    queueQ.offer(q.right);
                }
            }
        }
        return true;
    }

    // 判断p和q节点是否相同
    public boolean check(TreeNode p, TreeNode q) {
        // p and q are null
        if (p == null && q == null)
            return true;
        // one of p and q is null
        if (q == null || p == null)
            return false;
        // none of p and q is null
        if (p.val != q.val)
            return false;
        return true;
    }
}
