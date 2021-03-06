/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 *
 * 题目: 给定数组中第i个元素是一支股票的第i天的价格, 最多允许完成一笔交易, 返回能够获得最大利润
 *
 * 难度: easy
 * 
 * 思路: 1. 贪心算法, 记录目前为止的最小价格, 将这个最小价格作为买入价格, 然后将当前的价格作为售出价格, 查看当前收益是不是最大收益
 *      2. 动态规划
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int soFarMin = prices[0]; // 目前为止的最小价格
        int profit = 0; // 最大收益
        for (int price : prices) {
            if (price < soFarMin) {
                // 以当前价格买入, 更新目前为止的最小价格
                soFarMin = price;
            } else {
                // 以当前价格卖出, 更新最大利润
                profit = Math.max(price - soFarMin, profit);
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
            // dp[i][0], 第i天不持有股票, 可以获得的最大利润
            // 在只交易一次的情况下, 想要不持有股票的话, 要吗之前就不持有股票, 要吗之前持有今天卖掉
            // 今天不持有股票可以获得的最大利润 = max(昨天不持有股票并且今天不交易可以获得的最大利润, 昨天持有股票并且今天卖出股票可以获得的最大利润)
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1], 第i天持有股票, 可以获得的最大利润
            // 在只交易一次的情况下, 买入前不可能持有股票, 即要吗股票是i天之前买的, 要吗是第i天买的
            // 今天持有股票可以获得的最大利润 = max(昨天持有股票并且今天不交易可以获得的最大利润, 今天买入股票可以获得的最大利润)
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        // 最终获得的最大利润肯定是不持有股票时
        return dp_i_0; // dp[len - 1][0]
    }
}

