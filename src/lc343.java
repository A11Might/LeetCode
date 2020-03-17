/**
 * @author qhhu
 * @date 2019/11/6 - 9:29
 *
 * [343] 整数拆分
 *
 * 题目：将给定的一个正整数 n 拆分为至少两个正整数的和，使得这些正整数的乘积最大化，返回获得的最大乘积。
 *
 * 难度：medium
 *
 * 思路：划分型动态规划：将整数划分为 k 段。
 *      状态表示：dp[i] 表示将整数 i 划分 k 段后的最大乘积。
 *      状态转移方程：题意说至少要划分成两段，所以有两种情况：`划分成两段`和`划分成若干段（大于两段）`。
 *                  dp[n] = max(max(1 * (n - 1), 1 + dp[n - 1]), max(2 * (n - 2), 2 + dp[n - 2]), ...)
 *      初始状态：正整数 1 划分的最大乘积为 1。
 */
class Solution {
    /**
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];

        // 决策边界
        dp[1] = 1;

        // 状态转移
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }
}