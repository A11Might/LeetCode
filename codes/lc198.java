/*
 * @lc app=leetcode.cn id=198 lang=java
 *
 * [198] 打家劫舍
 * 
 * dp[i] = max(dp[i-2]+nums[i], dp[i-1])
 *     打劫当前屋子得到的最大金额，不打劫当前屋子得到的最大金额
 */
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (i - 2 >= 0) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            } else {
                dp[i] = Math.max(nums[i], dp[i - 1]);
            }
        }
        return dp[nums.length - 1];
    }
}

