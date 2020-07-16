/**
 * @author qhhu
 * @date 2019/10/11 - 18:17
 *
 * [129] 求根到叶子节点数字之和
 *
 * 题目：求给定二叉树所有从根到叶子节点的路径所代表的数字之和
 *
 * 难度：medium
 *
 * 思路：从根节点递归遍历整棵树，遍历时维护从根节点到该节点的路径表示的数，当遍历到叶节点时，将路径表示的数累加到答案中。
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private int ans = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int curNum) {
        if (root == null) return;
        curNum = curNum * 10 + root.val;
        if (root.left == null && root.right == null) {
            // 当前节点是叶子节点
            ans += curNum;
        } else {
            // 当前节点不是叶子节点，继续递归遍历
            dfs(root.left, curNum);
            dfs(root.right, curNum);
        }
    }
}