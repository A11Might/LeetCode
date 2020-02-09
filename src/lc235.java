/*
 * @lc app=leetcode.cn id=235 lang=java
 *
 * [235] 二叉搜索树的最近公共祖先
 * 
 * 题目: 在二叉搜索树中找到两个指定节点的最近公共祖先
 *
 * 难度: easy
 * 
 * 思路: 1. 递归, 从根节点开始遍历树, 如果节点p和节点q都在右子树上, 那么以右孩子为根节点继续遍历;
 *                                     如果节点p和节点q都在左子树上, 那么以左孩子为根节点继续遍历;
 *                                     如果节点p和节点q即不在左子树上也不在右子树上, 这就意味着我们已经找到节点p和节点q的LCA了
 *         f(root, p, q) = f(root.left, p, q) or f(root.right, p, q) or root
 *      2. 迭代, 遍历二叉树, 利用二叉搜索树节点大小的特性判断当前节点是否是公共祖先
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // If both p and q are lesser than parent
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If both p and q are greater than parent
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // We have found the split point, i.e. the LCA node
        return root;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // Value of p and Value of q
        int pVal = p.val, qVal = q.val;
        // Start from the root node of the tree
        TreeNode node = root;
        // Traverse the tree
        while (node != null) {
            // Value of ancestor/parent node.
            int parentVal = node.val;
            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }

        return null;
    }
}

