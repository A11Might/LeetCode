import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=106 lang=java
 *
 * [106] 从中序与后序遍历序列构造二叉树
 * 
 * 题目：根据一棵树的中序遍历与后序遍历构造二叉树(没有重复元素)
 *
 * 难度：medium
 * 
 * 思路：中序遍历，左子树的中序遍历序列-根节点-右子树的中序遍历序列
 *      后序遍历，左子树的中序遍历序列-右子树的中序遍历序列-根节点
 *      所以我们可以通过后序遍历序列知道根节点，然后将中序和后续遍历序列分为左子树和右子树部分，再递归重复建树操作
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private HashMap<Integer, Integer> pos = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            pos.put(inorder[i], i);
        }
        return buildTreeCore(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTreeCore(int[] inorder, int il, int ir, int[] postorder, int pl, int pr) {
        if (il > ir) return null;
        int rootValue = postorder[pr];
        TreeNode root = new TreeNode(rootValue);
        int indexInInorder = pos.get(rootValue);
        int leftSubTreeSize = indexInInorder - il;
        root.left = buildTreeCore(inorder, il, indexInInorder - 1, postorder, pl, pl + leftSubTreeSize - 1);
        root.right = buildTreeCore(inorder, indexInInorder + 1, ir, postorder, pl + leftSubTreeSize, pr - 1);
        return root;
    }
}

