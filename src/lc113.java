import java.util.ArrayList;
import java.util.Collections;
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
 * 思路：回溯
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
     * 空间复杂度: O(n) (n 为树的高度即递归栈的深度)
     */
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return Collections.emptyList();
        dfs(root, new ArrayList<>(), sum);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> sublist, int sum) {
        if (root == null) return;
        sublist.add(root.val);
        if (root.left == null && root.right == null) {
            // 找到一条路径和为目标和的路径
            if (root.val == sum) ans.add(new ArrayList<>(sublist));
        } else {
            // 当前节点不是叶子节点，递归调用函数, 判断其孩子节点是否存在 sum - node.val 的路径
            dfs(root.left, sublist, sum - root.val);
            dfs(root.right, sublist, sum - root.val);
        }
        sublist.remove(sublist.size() - 1); // 恢复现场
    }
}
