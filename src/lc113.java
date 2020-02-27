import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/11 - 18:03
 *
 * [113] 路径总和 II
 *
 * 题目：找到给定二叉树中从根节点到叶子节点路径总和等于给定值的所有路径
 *
 * 难度：medium
 *
 * 思路：同lc129
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, ans, new ArrayList<Integer>(), sum);

        return ans;
    }

    private void dfs(TreeNode root, List<List<Integer>> ans, ArrayList<Integer> subList, int curSum) {
        if (root == null) {
            return;
        }
        subList.add(root.val);
        // 到达叶子节点并且当前路径总和等于curSum
        if (root.left == null && root.right == null && root.val == curSum) {
            ans.add(new ArrayList<>(subList));
        } else {
            // 当前节点不是叶子节点，或当前路径总和不等于curSum，继续递归遍历
            dfs(root.left, ans, subList, curSum - root.val);
            dfs(root.right, ans, subList, curSum - root.val);
        }
        subList.remove(subList.size() - 1);
    }
}
