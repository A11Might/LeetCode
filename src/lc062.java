/*
 * @lc app=leetcode.cn id=62 lang=java
 *
 * [62] 不同路径
 * 
 * 题意：m x n的网格，只能向下向右移动，从左上角走到右上角总共有多少条不同的路径。
 *
 * 难度：medium
 * 
 * 思路：坐标型动态规划。
 *      状态表示：dp[i][j] 表示到达网格第 i 行第 j 列的路径数。
 *      状态转移方程：由题目中 每次只能向下或者向右移动一步 这句话得到，也就是 (i, j) 可以从 (i - 1, j) 或者 (i, j - 1) 走过来。
 *                  那么转移方程就是 dp[i][j] = dp[i - 1][j] + dp[i][j - 1]。
 *      初始状态：dp[1][1] = 1。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        // 决策边界
        dp[0][1] = 1;

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m][n];
    }
}