/**
 * @author qhhu
 * @date 2020/2/6 - 8:39
 *
 * [671] 二叉树中第二小的节点
 *
 * 题目: 给定一个非空特殊的二叉树, 返回所有节点中的第二小的值, 若不存在则返回-1.
 *      (非空特殊的二叉树每个结点都是正数, 并且每个结点的子结点数量只能为2或0. 若一个结点有两个子结点的话, 那这个结点的值不大于它的子结点的值)
 *
 * 难度: easy
 *
 * 思路: 通过深度优先搜索遍历树, 查看第二个最小值(第一个最小值必须是root.val)
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
    public int findSecondMinimumValue(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) return -1;
        // 题意当前结点要吗没有子结点, 要么就有两个子结点
        if (root.left == null && root.right == null) return -1;
        int left = root.left.val, right = root.right.val;
        if (root.left.val == root.val) left = dfs(root.left);
        if (root.right.val == root.val) right = dfs(root.right);
        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);
    }
}
