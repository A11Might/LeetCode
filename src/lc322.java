import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * 题目: 给定不同面额的硬币coins和总金额amount, 返回凑成总金额所需的最少的硬币个数
 *      (无法凑成时返回-1)
 *
 * 难度: medium
 *
 * 思路: 动态规划0-1背包问题
 *       状态转移方程: f(n) = min(1 + f(n - coins[i])), i in [0, coins.length)
 */
class Solution {
    /**
     * 时间复杂度: O(amount * coins.length)
     * 空间复杂度: O(amount)
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i]代表凑成i元所需的最少硬币数量
        int[] dp = new int[amount + 1];
        // 全部初始化为amount + 1, 硬币数最大也不会超过amount + 1, 以此来免除初始值在选取最小值时产生的影响
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            // 遍历每个硬币，依次比较出最少的个数
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 若dp[i]仍为初始值，则说明当前总金额不能由这些零钱构成，置为-1
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}