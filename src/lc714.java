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
        int[] cash = new int[len]; // 第i天不持有股票, 获得的最大利润
        int[] hold  = new int[len]; // 第i天持有股票, 获得的最大利润
        cash[0] = 0; // 今天不持有股票 = max(昨天不持有股票并且今天不交易, 昨天持有股票并且今天卖出股票)
        hold[0] = -prices[0]; // 今天持有股票 = max(昨天持有股票并且今天不交易, 昨天不持有股票并且今天买入股票)

        for (int i = 1; i < len; i++) {
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i] - fee); // 每次交易需要手续费
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
        }

        return cash[len - 1];
    }
}
