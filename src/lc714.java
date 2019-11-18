/**
 * @author qhhu
 * @date 2019/11/16 - 9:54
 *
 * [714] 买卖股票的最佳时机含手续费
 *
 * 题目: 给定一个 整数数组price, 其中第i个元素代表了第i天的股票价格, 非负整数fee代表了交易股票的手续费用
 *      (可以无限次地完成交易, 但是每次需支付手续费; 如果已经购买了一个股票, 卖出它前不能在继续购买股票)
 *
 * 难度: medium
 *
 * 思路: 动态规划
 */
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) {
            return 0;
        }

        int len = prices.length;
        // dp[i][0], 第i天不持有股票
        // dp[i][1], 第i天持有股票
        int[][] dp = new int[len][2];
        dp[0][0] = 0; // 第1天之前不可能持有股票, 所以第i天获得得最大利润为0
        dp[0][1] = -prices[0]; // 第1天持有股票可以获得的最大利润就是在第一天买下股票, 即-prices[0]

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee); // 买入卖出算一次交易, 每次交易需要手续费
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[len - 1][0];
    }
}