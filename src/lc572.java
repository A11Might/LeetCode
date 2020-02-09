/**
 * @author qhhu
 * @date 2020/2/6 - 8:38
 *
 * [572] 另一个树的子树
 *
 * 题目: 给定两个非空二叉树s和t, 判断s是否包含和t具有相同结构和结点值的子树
 *      (s的一个子树包括s的一个结点和这个结点的所有子孙; s也是自身的一棵子树)
 *
 * 难度: easy
 *
 * 思路: 递归(减而治之), 遍历s树的每个结点, 并判断以该结点为根结点的子树和t树是否具有相同结构和结点值
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return dfs(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // 判断以s和t为根结点的两棵树是否具有相同的结构和结点值
    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && dfs(s.left, t.left) && dfs(s.right, t.right);
    }
}