import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 * 
 * 题意：根据一棵树的前序遍历与中序遍历构造二叉树(树中没有重复元素)
 * 
 * 思路：先序遍历：根-左子树的先序遍历序列-右子树的先序遍历序列
 *      中序遍历：左子树的中序遍历序列-根节点-右子树的中序遍历序列
 * 通过先序遍历序列知道根节点，将先序和中序遍历序列分为左子树和右子树部分，再递归重复操作
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 用来获取值在inorder中的index
        // 方便用preorder中获得的子树头节点的值将inorder拆分为左子树序列和右子树序列
        Map<Integer, Integer> inMap = new HashMap<>(); 
        for (int i = 0; i < inorder.length; ++i) {
            inMap.put(inorder[i], i);
        }
        return process(inMap, preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode process(Map<Integer, Integer> inMap, int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) { // 一个就行了(当preStart > preEnd时一定有inStart > inEnd)
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndexInInorder = inMap.get(root.val); // 子树头结点在inorder中的index
        int offset = rootIndexInInorder - inStart; // inorder中的index不能直接用于preorder，需要转化 <-----我是干了
        root.left = process(inMap, preorder, inorder, preStart + 1, preStart + offset, inStart, rootIndexInInorder - 1);
        root.right = process(inMap, preorder, inorder, preStart + offset + 1, preEnd, rootIndexInInorder + 1, inEnd);
        return root;
    }
}
