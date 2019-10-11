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
 * 思路：同lc113
 *      1、递归，dfs
 *      2、迭代见lc112 dfs，lc257 bfs
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
    public int sumNumbers(TreeNode root) {
        int[] ans = {0}; // 使用数组(参数传递时不是值传递)very important
        dfs(root, 0, ans);

        return ans[0];
    }

    private void dfs(TreeNode root, int curNum, int[] ans) {
        if (root == null) {
            return;
        }
        curNum = curNum * 10 + root.val;
        // 当前节点是叶子节点
        if (root.left == null && root.right == null) {
            ans[0] += curNum;
        }
        // 当前节点不是叶子节点，继续递归遍历
        dfs(root.left, curNum, ans);
        dfs(root.right, curNum, ans);
    }
}
