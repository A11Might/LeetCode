import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=1008 lang=java
 *
 * [1008] 先序遍历构造二叉树
 * 
 * 题目：二叉搜索树的先序遍历序列还原二叉树
 * 
 * 思路：https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/solution/jian-kong-er-cha-shu-by-leetcode/
 *      1、递归
 *      2、迭代
 *      3、二叉搜索树的中序遍历序列是升序的，排序先序遍历序列即可得到中序遍历序列
 *          使用105.从前序与中序遍历序列构造二叉树的方法即可
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
    public int index = 0, n = 0;
    public int[] preorder;
    public TreeNode bstFromPreorder1(int[] preorder) {
        n = preorder.length;
        this.preorder = preorder;
        return process(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode process(int lo, int hi) {
        // if all elements from preorder are used
        // then the tree is constructed
        if (index == n) {
            return null;
        }
        int curVal = preorder[index];
        // 当index位置的值不满足条件未使用时，index的值不发生改变
        // if the current element
        // couldn't be placed here to meet BST requirments
        if (curVal < lo || hi < curVal) {
            return null;
        }
        // place the current element
        // and recursively construct subtrees
        TreeNode node = new TreeNode(curVal);
        index++; // <-----
        node.left = process(lo, curVal);
        node.right = process(curVal, hi);
        return node;
    }

    public TreeNode bstFromPreorder2(int[] preorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < n; i++) {
            // take the last element of the deque as a parent
            // and create a child from the next preorder element
            TreeNode father = stack.peek(); // 记录当前遍历到数组中元素的父节点
            TreeNode children = new TreeNode(preorder[i]);
            // adjust the parent
            while (!stack.isEmpty() && stack.peek().val < children.val) {
                father = stack.pop();
            }
            // folow BST logic to create a  parent-child link
            if (father.val > children.val) {
                father.left = children;
            } else {
                father.right = children;
            }
            // add the child into deque
            stack.push(children);
        }
        return root;
    }
}

