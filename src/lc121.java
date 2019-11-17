/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 *
 * 题目: 给定数组中第i个元素是一支股票的第i天的价格, 最多允许完成一笔交易, 返回能够获得最大利润
 *
 * 难度: easy
 * 
 * 思路: 动态规划, 前i天的最大收益 = max(前i-1天的最大收益，第i天的价格-前i-1天中的最小价格)
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int ans = 0; // 第i天的最大收益
        int minPrices = Integer.MAX_VALUE; // 第i天前的最小价格
        for (int i = 0; i < prices.length; i++) {
            // 第i天的最大收益 = max(第i-1天的最大收益，第i天的价格-前i-1天中的最小价格)
            ans = Math.max(ans, prices[i] - minPrices);
            minPrices = Math.min(minPrices, prices[i]); // 更新当前最小价格
        }

        return ans;
    }
}

