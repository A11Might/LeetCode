/*
 * @lc app=leetcode.cn id=329 lang=java
 *
 * [329] 矩阵中的最长递增路径
 * 
 * 题意：给定一个整数矩阵，找出最长递增路径的长度(可以往上，下，左，右四个方向移动)
 * 
 * https://www.bilibili.com/video/av48832928
 * 思路：动态规划，f(a, b) = Math.max(f(a, b - 1), f(a, b + 1), f(a - 1, b), f(a + 1, b))
 */
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dp[i][j] == 0) {
                    dfs(matrix, i, j, dp, Integer.MIN_VALUE);
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        return res;
    }

    /**
     * 从当前元素向上下左右四个方向，dfs
     * 
     * @param matrix
     * @param row 当前元素所在行
     * @param col 当前元素所在列
     * @param dp 
     * @param prev 前一个元素的大小，用于比较是否满足递增条件
     * @return
     */
    private int dfs(int[][] matrix, int row, int col, int[][] dp, int prev) {
        if (row < 0 || row > matrix.length - 1 ||
            col < 0 || col > matrix[0].length - 1 ||
            matrix[row][col] <= prev) {
                return 0;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        // f(a, b) = Math.max(f(a, b - 1), f(a, b + 1), f(a - 1, b), f(a + 1, b))
        int left = dfs(matrix, row, col - 1, dp, matrix[row][col]);
        int right = dfs(matrix, row, col + 1, dp, matrix[row][col]);
        int up = dfs(matrix, row - 1, col, dp, matrix[row][col]);
        int down = dfs(matrix, row + 1, col, dp, matrix[row][col]);

        dp[row][col] = Math.max(left, Math.max(right, Math.max(up, down))) + 1;
        return dp[row][col];
    }
}

