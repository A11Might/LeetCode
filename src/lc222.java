/**
 * @author qhhu
 * @date 2019/10/9 - 10:05
 *
 * [222] 完全二叉树的节点个数
 *
 * 题目：求给定完全二叉树的节点个数
 *
 * 难度：medium
 *
 * 思路：判断左右子树的高度，
 *      如果相等说明左子树是满二叉树, 然后进一步判断右子树的节点数(最后一层最后出现的节点必然在右子树中)
 *      如果不等说明说明右子树是满二叉树, 然后进一步判断左子树的节点数(最后一层最后出现的节点必然在左子树中)
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
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        if (ld == rd) {
            // 1(根节点) + (1 << ld) - 1(左完全左子树节点数) + 右子树节点数量
            return (1 << ld) + countNodes(root.right);
        } else {
            // 1(根节点) + (1 << rd) - 1(右完全右子树节点数) + 左子树节点数量
            return (1 << rd) + countNodes(root.left);
        }

    }

    // 获得以当前节点为头结点的子树的最左叶结点的深度
    private static int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;
        }

        return depth;
    }
}
