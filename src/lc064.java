/*
 * @lc app=leetcode.cn id=64 lang=java
 *
 * [64] 最小路径和
 *
 * 题目：在给定的包含非负整数的 m * n 的网格中，找到一条从左上角到右下角的路径，使得路径的数字总和最小。
 *
 * 难度：medium
 *
 * 思路：坐标型动态规划。
 *      状态表示：dp[i][j] 表示位于网格第 i 行第 j 列的最小路径和。
 *      状态转移方程：由题目中 每次只能向下或者向右移动一步 这句话得到，也就是 (i, j) 可以从 (i - 1, j) 或者 (i, j - 1) 走过来。
 *                  那么转移方程就是 dp[i][j] = min(dp[i - 1][j], dp[i][j - 1] + grid[i][j])。
 *      初始状态：dp[0][0] = grid[0][0]；dp[i][0] = dp[i - 1][0] + grid[i][0]；dp[0][j] = dp[0][j - 1] + grid[0][j]。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        // 决策边界
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < cols; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];

        // 状态转移
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[rows - 1][cols - 1];
    }
}