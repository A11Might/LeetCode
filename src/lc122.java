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
 * 思路: 动态规划
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int len = prices.length;
        int[] hold = new int[len]; // 第i天持有股票, 获得的最大利润
        int[] cash = new int[len]; // 第i天不持有股票, 获得的最大利润
        hold[0] = -prices[0];
        cash[0] = 0; // 第1天之前不可能持有股票, 所以第i天获得得最大利润为0
        for (int i = 1; i < len; i++) {
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]); // 今天持有股票 = max(昨天持有股票并且今天不交易, 昨天不持有股票并且今天买入股票)
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]); // 今天不持有股票 = max(昨天不持有股票并且今天不交易, 昨天持有股票并且今天卖出股票)
        }

        return cash[len - 1];
    }
}

