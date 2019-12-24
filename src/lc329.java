/*
 * @lc app=leetcode.cn id=329 lang=java
 *
 * [329] 矩阵中的最长递增路径
 * 
 * 题目: 给定一个整数矩阵，找出最长递增路径的长度(可以往上，下，左，右四个方向移动)
 *
 * 难度: hard
 * 
 * https://www.bilibili.com/video/av48832928
 * 思路：dfs, 利用之前搜索的结果, 简化这次搜索操作
 */
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;
        // dp[i][j]为从[i, j]出发能找到的最长递增路径的长度
        int[][] dp = new int[rows][cols];
        int res = 0; // 最长递增路径长度
        // 从矩阵中的每个点开始dfs, 并更新当前最长递增路径长度
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(matrix, i, j, dp, Integer.MIN_VALUE);
                res = Math.max(res, dp[i][j]);
            }
        }

        return res;
    }

    // dfs, 从[row][col]出发找的最长递增路径的长度
    // pre记录上个节点的值, 用于判断是否男足递增路径的条件(初始化为Integer.MIN_VALUE)
    private int dfs(int[][] matrix, int row, int col, int[][] dp, int pre) {
        // 越界或不满足递增条件
        if (row >= matrix.length || row < 0 ||
                col >= matrix[0].length || col < 0 ||
                matrix[row][col] <= pre) return 0;
        // 当前位置dfs过, 最长递增路径的长度已知, 直接返回
        if (dp[row][col] != 0) return dp[row][col];

        /**
         * [
         *   [(9), 9, 4],
         *   [(6), 6, 8],
         *   [(2),(1),1]
         * ]
         * 则f(2, 1) = f(2, 0) + 1
         */
        int left = dfs(matrix, row - 1, col, dp, matrix[row][col]);
        int right = dfs(matrix, row + 1, col, dp, matrix[row][col]);
        int up = dfs(matrix, row, col - 1, dp, matrix[row][col]);
        int down = dfs(matrix, row, col + 1, dp, matrix[row][col]);
        // f(a, b) = Math.max(f(a, b - 1), f(a, b + 1), f(a - 1, b), f(a + 1, b)) + 1
        dp[row][col] = 1 + Math.max(left, Math.max(right, Math.max(up, down)));

        return dp[row][col];
    }
}

