/**
 * @author qhhu
 * @date 2020/7/15 - 8:57
 *
 * [124] 二叉树中的最大路径和
 *
 * 题目：给定一个非空二叉树，返回其最大路径和。
 *      （路径定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点）
 *
 * 难度：hard
 *
 * 思路：对于任意一个节点，如果最大和路径包含该节点，那么只可能是两种情况：
 *      1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点构成最大路径
 *      2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
 *      所以我们递归遍历整棵树，求经过每个节点的最大路径，取一个最大值。
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
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left)); // 如果子树路径和为负则应当置 0 表示最大路径不包含子树
        int right = Math.max(0, dfs(root.right));
        max = Math.max(max, left + right + root.val); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        return Math.max(left, right) + root.val;
    }
}
