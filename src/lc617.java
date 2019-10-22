import java.util.Stack;

/*
 * @lc app=leetcode.cn id=617 lang=java
 *
 * [617] 合并二叉树
 * 
 * 将tree1和tree2合并到t1上
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
    // 递归
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        return process(t1, t2);
    }

    private TreeNode process(TreeNode node1, TreeNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        node1.val += node2.val; // 先合并根节点
        node1.left = process(node1.left, node2.left); // 再递归合并左子树
        node1.right = process(node1.right, node2.right); // 再递归合并右子树
        return node1;
    }

    // 迭代
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) // <-----这里
            return t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] { t1, t2 });
        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            if (cur[1] == null) { // 这里的cur[0]不可能为null(如图)，t[1]为null时不用动,tree1当前节点即为合并树的节点
                continue;
            }
            cur[0].val += cur[1].val;
            if (cur[0].left == null) { // <-----这里 
                cur[0].left = cur[1].left; // 当tree1.left为空时，tree1节点直接接在tree2上，即为合并后的树。牛逼
            } else {
                stack.push(new TreeNode[] { cur[0].left, cur[1].left });
            }
            if (cur[0].right == null) { // <-----这里
                cur[0].right = cur[1].right; // 当tree1.right为空时，tree1节点直接接在tree2上，即为合并后的树。牛逼
            } else {
                stack.push(new TreeNode[] { cur[0].right, cur[1].right });
            }
        }
        return t1;
    }

}

