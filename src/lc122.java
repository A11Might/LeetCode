/*
 * @lc app=leetcode.cn id=122 lang=java
 *
 * [122] 买卖股票的最佳时机 II
 *
 * 题目: 给定数组中第i个元素是一支股票的第i天的价格, 可以尽可能多的完成交易, 返回能够获得最大利润
 *      (不能同时参与多笔交易（即必须在再次购买前出售掉之前的股票）)
 *
 * 难度: easy
 *
 * 思路: 1. 贪心算法, 当前prices[i] > prices[i-1], 就把prices[i] - prices[i-1]添加到收益中
 *      2. 动态规划
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                // 当前prices[i] > prices[i-1], 就把prices[i] - prices[i-1]添加到收益中
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length <= 1) { // 实例 []
            return 0;
        }

        int len = prices.length;
        // dp[0][0], 第1天之前不可能持有股票, 即第1天没有股票可以买钱, 所以第1天不持有股票可以获得的最大利润为0
        // dp[0][1], 第1天持有股票可以获得的最大利润就是在第一天买下股票, 即-prices[0]
        int dp_i_0 = 0, dp_i_1 = -prices[0];

        for (int i = 1; i < len; i++) {
            int temp = dp_i_0;
            // dp[i][0], 第i天不持有股票, 可以获得的最大利润
            // 今天不持有股票可以获得的最大利润 = max(昨天不持有股票并且今天不交易可以获得的最大利润, 昨天持有股票并且今天卖出股票可以获得的最大利润)
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1], 第i天持有股票, 可以获得的最大利润
            // 今天持有股票可以获得的最大利润 = max(昨天持有股票并且今天不交易可以获得的最大利润, 昨天不持有股票并且今天买入股票可以获得的最大利润)
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }

        // 最终获得的最大利润肯定是不持有股票时
        return dp_i_0; // dp[len - 1][0]
    }
}

