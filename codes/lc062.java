/*
 * @lc app=leetcode.cn id=62 lang=java
 *
 * [62] 不同路径
 * 
 * 题意：mxn网格，只能向下向右移动，从左上角走到右上角总共有多少条不同的路径
 * 
 * 思路：
 * 1、回溯：超时了
 * 2、动态规划：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
 */
class Solution {
    public int count;

    public int uniquePaths1(int m, int n) { // m为列数，n为行数
        count = 0;
        process(m, n, 1, 1);
        return count;
    }

    private void process(int m, int n, int x, int y) {
        if (x == m && y == n) { // 到达终点，路径加一
            count++;
        } else if (x == m) { // 到达最后一列只能往下走，路径加一
            count++;
        } else if (y == n) { // 到达最后一行最能往右走，路径加一
            count++;
        // 一般情况
        } else {
            process(m, n, x + 1,y); // 向右走
            process(m, n, x, y + 1); // 向下走
        }
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // 只有一行或一列时，只有一种路径
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                // 一般情况
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

