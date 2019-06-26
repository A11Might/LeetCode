/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 * 
 * 题目：找到若干个完全平方数使其和等于n，求完全平方数个数最少几个(任何数都可以由若干个完全平方数组成，1)
 * 
 * 思路：动态规划，f(n) = 1 + min{f(n-1^2), f(n-2^2), f(n-3^2), f(n-4^2), ... , f(n-k^2) //(k为满足k^2<=n的最大的k)}
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int minVal = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                minVal = Math.min(minVal, dp[i - (j * j)]);
            }
            dp[i] = minVal + 1; // 加上j * j这个完全平方数
        }
        return dp[n];
    }
}

