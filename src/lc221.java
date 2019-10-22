/*
 * @lc app=leetcode.cn id=221 lang=java
 *
 * [221] 最大正方形
 * 
 * 题意：在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积
 * 
 * 思路：动态规划，dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
 */
class Solution {
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxR = 0;
        for (int i = 0; i < rows; ++i) {
            dp[i][0] = matrix[i][0] - 48; // 字符转整数
        }
        for (int j = 0; j < cols; ++j) {
            dp[0][j] = matrix[0][j] - 48;
        }

        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                maxR = Math.max(maxR, dp[i][j]);
            }
        }
        return maxR * maxR;
    }
}

