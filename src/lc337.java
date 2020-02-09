/**
 * @author qhhu
 * @date 2019/11/15 - 9:34
 *
 * [337] 打家劫舍 III
 *
 * 题目: 所有房屋排列类似二叉树, 返回在不触动劲爆的情况下, 小偷一晚上能够偷盗的最高金额
 *      (若两个直接相连的房子在同一天晚上被打劫, 房屋将自动报警)
 *
 * 难度: medium
 *
 * 思路: 树型dp, ans[0]代表`不偷盗`当前节点可以获得的最大金额, ans[1]代表`可以偷盗`当前节点可以过得的最大金额
 *             `可以偷盗`的意思是当前房屋可以偷盗也可以不偷盗
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] ans = new int[2];
        ans[0] = left[1] + right[1];
        // 可以偷盗当前房屋的情况下, 可以选择不偷盗当前房屋
        ans[1] = Math.max(ans[0], root.val + left[0] + right[0]);

        return ans;
    }
}
