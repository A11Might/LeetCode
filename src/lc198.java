/*
 * @lc app=leetcode.cn id=198 lang=java
 *
 * [198] 打家劫舍
 *
 * 题目: 给定一个代表每个房屋存放金额的非负整数数组, 计算在不触动警报装置的情况下, 能够窃取的最高金额
 *      (如果相邻的两个房屋在同一个晚上被偷, 系统会报警)
 *
 * 难度: easy
 * 
 * 思路: 动态规划, 状态转移方程: dp[i] = max(dp[i-2]+nums[i], dp[i-1]), dp[i]表示抢劫nums[0, i]所能获得的最大收益
 *                              打劫当前屋子得到的最大金额，不打劫当前屋子得到的最大金额
 */
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (i - 2 < 0) {
                dp[i] = Math.max(nums[i], dp[i - 1]);
            } else {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
        }

        return dp[nums.length - 1];
    }
}

