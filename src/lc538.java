import java.util.Stack;

/*
 * @lc app=leetcode.cn id=538 lang=java
 *
 * [538] 把二叉搜索树转换为累加树
 * 
 * 以右->中->左的顺序遍历二叉树，即先序遍历倒过来的顺序
 * 将遍历顺序的前一个结点的累加值记录起来，和当前结点相加，得到当前结点的累加值
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
    private int preSum = 0; // 每层递归用的都是这个sum，定义为全局变量
    public TreeNode convertBST1(TreeNode root) {
        reversePreOrder(root);
        return root;
    }

    private void reversePreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        reversePreOrder(node.right);
        node.val += preSum;
        preSum = node.val;
        reversePreOrder(node.left);
    }

    // 迭代
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            cur.val += sum;
            sum = cur.val;
            cur = cur.left;
        }
        return root;
    }
}

