/*
 * @lc app=leetcode.cn id=62 lang=java
 *
 * [62] 不同路径
 * 
 * 题意：m x n的网格，只能向下向右移动，从左上角走到右上角总共有多少条不同的路径
 *
 * 难度: medium
 * 
 * 思路：1、回溯：超时了
 *      2、动态规划: 状态转移方程, f(m, n) = f(m + 1, n) + f(m, n + 1)
 */
class Solution {
    public int count;
    public int uniquePaths1(int m, int n) { // m为列数，n为行数
        count = 0;
        dfs(m, n, 1, 1);
        return count;
    }

    private void dfs(int m, int n, int x, int y) {
        if (x == m && y == n) { // 到达终点，路径加一
            count++;
        } else if (x == m) { // 到达最后一列只能往下走，路径加一
            count++;
        } else if (y == n) { // 到达最后一行最能往右走，路径加一
            count++;
        // 一般情况
        } else {
            dfs(m, n, x + 1,y); // 向右走
            dfs(m, n, x, y + 1); // 向下走
        }
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n - 1] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[m - 1][i] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // f(m, n) = f(m + 1, n) + f(m, n + 1)
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }

        return dp[0][0];
    }
}

