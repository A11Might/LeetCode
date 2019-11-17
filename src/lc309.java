/**
 * @author qhhu
 * @date 2019/11/16 - 9:46
 *
 * [309] 最佳买卖股票时机含冷冻期
 *
 * 题目: 给定一个整数数组, 其中第i个元素代表了第i天的股票价格, 返回最大利润
 *      (在不能同时参与多笔交易和卖出股票后无法在第二天买入股票的条件下, 可以尽可能地完成更多的交易)
 *
 * 难度: medium
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
        cash[0] = 0; // 第1天之前不可能持有股票, 所以第i天获得得最大利润为0
        hold[0] = -prices[0];
        cash[1] = Math.max(cash[0], hold[0] + prices[1]); // 今天不持有股票 = max(昨天不持有股票并且今天不交易, 昨天持有股票并且今天卖出股票)
        hold[1] = Math.max(cash[0] - prices[1], hold[0]); // 今天持有股票 = max(昨天持有股票并且今天不交易, 昨天不持有股票并且今天买入股票)

        for (int i = 2; i < len; i++) {
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], cash[i - 2] - prices[i]); // 卖出股票后，你无法在第二天买入股票
        }

        return cash[len - 1];
    }
}
