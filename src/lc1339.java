/**
 * @author qhhu
 * @date 2020/2/10 - 18:35
 *
 * [1339] 分裂二叉树的最大乘积
 *
 * 题目: 给定一棵二叉树, 请你删除1条边使得二叉树分裂成两棵子树, 返回它们子树和的乘积最大值
 *
 * 难度: medium
 *
 * 思路: 1. 要求两棵子树和的最大乘积, 那就得先求出两棵子树各自的和.
 *      2. 分别求两棵子树的和显然很难做到, 但我们可以很方便的其中一个子树的和.
 *      3. 用整棵树的总和减去求出的子树和, 就得到了另一棵子树和.
 *      4. 枚举所有子树, 对乘积取最大.
 *
 * 这里使用后序遍历的同时求子树和，其实是一个累加的过程。利用前一次递归求好子树和(leftSum, rightSum)，加上当前结点值root->val，就得到了以当前结点为根节点的子树和。
 *
 * 这样做，比起以每个结点为根结点再单独进行子树求和，要减少大量的重复计算
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
    private long allSum;
    private long maxProduct;
    public int maxProduct(TreeNode root) {
        if (root == null) return 0;
        allSum = sum(root);
        dfs(root);
        return (int)(maxProduct % (1e9 + 7));
    }

    /**
     * 这里使用后序遍历的同时求子树和, 其实是一个累加的过程. 利用前一次递归求好子树和(leftSum, rightSum), 加上当前结点值root->val,
     * 就得到了以当前结点为根节点的子树和. 这样做, 比起以每个结点为根结点再单独进行子树求和(超时), 要减少大量的重复计算.
     * 在求子树和时(int left = dfs(root.left);)已经将子树的所有节点作为分裂出的的树并计算乘积;
     * 若使用sum()求和, 后续的dfs还会再次遍历树.
     */
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int sum = left + root.val + right;
        maxProduct = Math.max(maxProduct, (allSum - sum) * sum);
        return sum;
    }

    private long sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }
}