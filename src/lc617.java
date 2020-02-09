import java.util.Stack;

/*
 * @lc app=leetcode.cn id=617 lang=java
 *
 * [617] 合并二叉树
 *
 * 题目: 将给定的两个二叉树合并
 *      (若两个结点重叠则将它们的值相加作为节点合并后的新值, 否则不为null的节点将直接作为新二叉树结点)
 *
 * 难度: easy
 *
 * 思路: 将tree1和tree2合并到tree1上(也可以新建一个树), 1. 递归(减而治之)
 *                                              2. 迭代
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return dfs(t1, t2);
    }

    private TreeNode dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val; // 先合并当前两个节点
        t1.left = dfs(t1.left, t2.left); // 再递归合并左子树
        t1.right = dfs(t1.right, t2.right); // 再递归合并右子树
        return t1;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度)
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2; // <-----这里
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] { t1, t2 });
        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            // t[1]为null时不用动, t1当前节点即为合并树的节点
            // 这里的cur[0]不可能为null(如箭头所示)
            if (cur[1] == null) continue;
            cur[0].val += cur[1].val;
            if (cur[0].left == null) { // <-----这里
                // 当t1.left为空时, t1节点直接接在t2上, 即为合并后的树。牛逼
                cur[0].left = cur[1].left;
            } else {
                // 否则就迭代去合并这两个节点
                stack.push(new TreeNode[] { cur[0].left, cur[1].left });
            }
            if (cur[0].right == null) { // <-----这里
                // 当t1.right为空时, t1节点直接接在t2上, 即为合并后的树。牛逼
                cur[0].right = cur[1].right;
            } else {
                // 否则就迭代去合并这两个节点
                stack.push(new TreeNode[] { cur[0].right, cur[1].right });
            }
        }
        return t1;
    }

}

