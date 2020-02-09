/**
 * @author qhhu
 * @date 2020/2/6 - 8:39
 *
 * [687] 最长同值路径
 *
 * 题目: 在给定的二叉树中, 找到每个结点值都相同的最长路径
 *      (这条路径可以经过也可以不经过根结点)
 *
 * 难度: easy
 *
 * 思路: 题目所求的最长路径肯定经过给定二叉树中的一个结点, 向左下延伸,向右下延伸又或向两侧延伸.
 *      所以我们可以遍历二叉树的每个结点, 找到从该结点出发向下延伸, 并且与其值相同的最大深度(向左下延伸,向右下延伸),
 *      以此来更新每个结点值都相同的最长路径(向左下延伸,向右下延伸又或向两侧延伸).
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
     * 空间复杂度: O(h) O(n) (n为树的高度即递归栈的深度)
     */
    private int maxPath = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxPath;
    }

    // 返回从root结点出发, 向下延伸与其值相同的最大深度
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftPath = 0, rightPath = 0;
        if (root.left != null && root.left.val == root.val) {
            leftPath = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightPath = right + 1;
        }
        // 更新每个结点值都相同的最长路径
        maxPath = Math.max(maxPath, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}
