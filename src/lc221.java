/*
 * @lc app=leetcode.cn id=221 lang=java
 *
 * [221] 最大正方形
 * 
 * 题意：在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积
 *
 * 难度: medium
 * 
 * 思路：动态规划, dp[i, j]表示以matrix[i][j]为右下角只包含 1 的最大正方形的边长
 *                dp(i, j) = min(dp(i − 1, j), dp(i − 1, j − 1), dp(i, j − 1)) + 1, 画图就可以理解
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n) (空间最优解O(n)https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/)
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        // 由于需要用到i - 1, 则将dp数组多设置一行一列
        // 相当于i == 0时, dp[row - 1][col - 1], dp[row - 1][col], dp[row][col - 1]都等于0
        int[][] dp = new int[rows + 1][cols + 1];
        int ans = Integer.MIN_VALUE;
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                if (matrix[row - 1][col - 1] == '1') {
                    dp[row][col] = Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1])) + 1;
                    ans = Math.max(dp[row][col], ans);
                }
            }
        }

        return ans * ans;
    }
}

