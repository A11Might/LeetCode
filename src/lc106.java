import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=106 lang=java
 *
 * [106] 从中序与后序遍历序列构造二叉树
 * 
 * 题目：根据一棵树的中序遍历与后序遍历构造二叉树(没有重复元素)
 * 
 * 思路：中序遍历，左子树的中序遍历序列-根节点-右子树的中序遍历序列
 *      后序遍历，左子树的中序遍历序列-右子树的中序遍历序列-根节点
 * 通过后序遍历序列知道根节点，将中序遍历和后序遍历序列分为左子树和右子树部分，再递归重复操作
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 用来获取值在inorder中的index
        // 方便用postorder中获得的子树头节点的值将inorder拆分为左子树序列和右子树序列
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return process(inMap, inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode process(Map<Integer, Integer> inMap, int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[postEnd]);
        int nodeIndexInInorder = inMap.get(node.val); // node在inorder中的index
        int offset = nodeIndexInInorder - inStart; // 左子树节点个数
        node.left = process(inMap, inorder, postorder, inStart, nodeIndexInInorder - 1, postStart, postStart + offset - 1);
        node.right = process(inMap, inorder, postorder, nodeIndexInInorder + 1, inEnd, postStart + offset, postEnd - 1);
        return node;
    }
}

