/**
 * @author qhhu
 * @date 2019/11/15 - 9:34
 *
 * [337] 打家劫舍 III
 *
 * 题目: 所有房屋排列类似二叉树, 在不触动劲爆的情况下, 小偷一晚上能够偷盗的最高金额
 *      (若两个直接相连的房子在同一天晚上被打劫, 房屋将自动报警)
 *
 * 难度: medium
 *
 * 思路: 树型dp, ans[0]代表不偷盗当前节点可以获得的最大金额, ans[1]代表偷盗当前节点可以过得的最大金额
 */
class Solution {
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int[] ans = new int[2];
        ans[0] = left[1] + right[1];
        // 偷盗当前房屋的情况下, 可以选择不偷盗当前房屋
        ans[1] = Math.max(ans[0], node.val + left[0] + right[0]);

        return ans;
    }
}
