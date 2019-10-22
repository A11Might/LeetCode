/**
 * @author qhhu
 * @date 2019/10/15 - 23:08
 *
 * [236] 二叉树的最近公共祖先
 *
 * 题目：在给定二叉树中找到两个指定节点的最近公共祖先
 *
 * 难度：medium
 *
 * 思路：递归
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
    // 在root中寻找p和q
    // 如果p和q都在root所在的二叉树中, 则返回LCA
    // 如果p和q只有一个在root所在的二叉树中, 则返回p或者q
    // 如果p和q均不在root所在的二叉树中, 则返回NULL
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }

        return null;
    }


    private TreeNode ret = null;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);

        return ret;
    }

    // 在root中寻找p和q, 如果包含则返回 true
    // 否则返回false
    //
    // root是p或者q；root的左子树包含p或q；root的右子树包含p或q；三个条件有两个满足
    // 则 ret = root
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        int mid = (root == p || root == q) ? 1 : 0;
        int left = dfs(root.left, p, q) ? 1 : 0;
        int right = dfs(root.right, p, q) ? 1 : 0;

        if (mid + left + right >= 2) {
            ret = root;
        }

        return mid + left + right > 0;
    }
}
