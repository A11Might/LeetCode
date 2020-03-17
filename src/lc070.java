/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 *
 * 题目：n 阶楼梯，每次可以爬 1 或 2 个台阶，有多少种方法可以爬完。
 *
 * 难度：easy
 *
 * 思路：序列型动态规划：爬楼梯有一种从下往上的顺序。
 *      状态表示： dp[i] 表示到达第 i 级台阶的方案数。
 *      状态转移方程：每次可以爬 1 或 2 个台阶，dp[i] = dp[i - 1] + dp[i - 2]
 *      初始状态：上 0 级台阶有一种方案；上 1 级台阶有 1 中方案。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int climbStairs1(int n) {
        int[] dp = new int[n + 1];

        // 决策边界
        dp[0] = dp[1] = 1;

        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int climbStairs2(int n) {
        // 复用 pre1 和 pre2 表示当前状态的前两个状态。
        int pre2 = 1, pre1 = 1;
        for (int i = 2; i <= n; i++) {
            int temp = pre1 + pre2;
            pre1 = pre2;
            pre2 = temp;
        }

        return pre2;
    }
}