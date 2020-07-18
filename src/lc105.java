import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 * 
 * 题意：根据一棵树的前序遍历与中序遍历构造二叉树(树中没有重复元素)
 *
 * 难度：中等
 * 
 * 思路：先序遍历：根-左子树的先序遍历序列-右子树的先序遍历序列
 *      中序遍历：左子树的中序遍历序列-根节点-右子树的中序遍历序列
 *      所以我们可以通过先序遍历序列知道根节点，然后将先序和中序遍历序列分为左子树和右子树部分，再递归重复建树操作
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            pos.put(inorder[i], i);
        }
        return buildTreeCore(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeCore(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
        if (pl > pr) return null;
        int rootValue = preorder[pl];
        TreeNode root = new TreeNode(rootValue);
        int indexInInorder = pos.get(rootValue);
        int leftSubTreeSize = indexInInorder - il;
        root.left = buildTreeCore(preorder, pl + 1, pl + leftSubTreeSize, inorder, il, indexInInorder - 1);
        root.right = buildTreeCore(preorder, pl + leftSubTreeSize + 1, pr, inorder, indexInInorder + 1, ir);
        return root;
    }
}