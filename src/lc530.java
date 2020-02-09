/**
 * @author qhhu
 * @date 2020/2/9 - 9:15
 *
 * [530] 二叉搜索树的最小绝对值
 *
 * 题目: 给定所有结点值均为非负值的二叉搜索树, 返回树中任意两个结点的差值的绝对值的最小值
 *
 * 难度: easy
 *
 * 思路: 二叉搜索树的中序遍历序列为升序, 所以中序遍历给定二叉搜索树, 计算所有相邻两结点之差的绝对值, 其中的最小值即为答案
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
    private int minDiff = Integer.MAX_VALUE;
    private TreeNode preNode = null;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minDiff;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (preNode != null) minDiff = Math.min(minDiff, node.val - preNode.val);
        preNode = node;
        dfs(node.right);
    }
}
