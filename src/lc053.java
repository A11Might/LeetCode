/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 * 
 * 题目：给一个数组，返回它的最大连续子数组的和(要求时间复杂度O(n))
 *
 * 难度: easy
 * 
 * 思路：动态规划，f(i)表示以i个元素结尾的子数组的最大和
 *       状态转移方程: f(i) = nums[i] + f(i - 1) if f(i - 1) > 0
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (空间最优解为O(1))
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int ans = Integer.MIN_VALUE;
        if (len == 0) {
            return ans;
        }

        // dp[i]表示以i元素结尾的子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];
        ans = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = nums[i];
            if (dp[i - 1] > 0) {
                dp[i] += dp[i - 1];
            }
            // 实时更新最大连续子数组的和
            ans = dp[i] > ans ? dp[i] : ans;
        }

        return ans;
    }
}

