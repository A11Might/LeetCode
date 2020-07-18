import java.util.ArrayDeque;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=101 lang=java
 *
 * [101] 对称二叉树
 *
 * 题目: 判断给定二叉树是否是对称二叉树
 *
 * 难度: easy
 *
 * 思路: 判断给定二叉树的左右子树是否是镜像二叉树即可，
 *      两个树互为镜像的条件：a. 它们的两个根结点具有相同的值；
 *                        b. 每个树的右子树都与另一个树的左子树镜像对称(牛逼, 还以为少了左子树与右子树对称能)
 *      1. 递归(减而治之),
 *         f(left, right) = if left.val == right.val && f(left.left, right.right) && f(left.right, right.right)
 *      2. 迭代, 类似于 BFS, 每次取出两个结点并比较它们的值, 将两个结点的左右子结点按相反的顺序插入队列中(将需要相等的节点放在一起)
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // 判断左右两个子树是否为对称二叉树
        return dfs(root.left, root.right);
    }

    // 判断以 root1 和 root2 为根结点的两棵树是否互为镜像
    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null
                || root1.val != root2.val) return false;
        return dfs(root1.left, root2.right) && dfs(root1.right, root2.left);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 将对称的节点, 放在相邻的位置方便比较
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode cur1 = queue.poll();
            TreeNode cur2 = queue.poll();
            // 判断对称节点是否相等
            if (cur1 == null && cur2 == null) continue;
            if (cur1 == null || cur2 == null) return false;
            if (cur1.val != cur2.val) return false;
            // 将对称的节点, 放在相邻的位置方便比较
            queue.add(cur1.left);
            queue.add(cur2.right);
            queue.add(cur1.right);
            queue.add(cur2.left);
        }
        return true;
    }
}

