/**
 * @author qhhu
 * @date 2019/10/13 - 17:51
 *
 * [450] 删除二叉搜素树中的节点
 *
 * 题目：删除给定二叉搜素树中给定值key对应的节点，并保证二叉搜素树的性质不变
 *      (要求时间复杂度为O(h)，h为树的高度)
 *
 * 难度：medium
 *
 * 思路：利用二叉搜素树的性质找到key对应节点，将其替换为右子树最左叶子
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 待删除节点在右子树中
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        // 待删除节点在左子树中
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        // key == root.val，root 为待删除节点
        // 返回右子树作为新的根
        if (root.left == null) {
            return root.right;
            // 返回左子树作为新的根
        } else if (root.right == null) {
            return root.left;
            // 左右子树都存在，返回后继节点（右子树最左叶子）作为新的根
        } else {
            TreeNode curRoot = min(root.right);
            // curRoot.right = deleteMin(root.right) 需在 curRoot.left = root.left操作之前
            // 否则会形成环
            curRoot.right = deleteMin(root.right);
            curRoot.left = root.left;
            return curRoot;
        }
    }

    // 找到当前树中最小节点
    private TreeNode min(TreeNode root) {
        if (root.left == null) {
            return root;
        }

        return min(root.left);
    }

    // 删除当前树中最小节点
    private TreeNode deleteMin(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);

        return root;
    }
}
