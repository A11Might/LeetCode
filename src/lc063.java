/**
 * @author qhhu
 * @date 2019/11/8 - 9:16
 *
 * [63] 不同路径 II
 *
 * 题目: m x n的网格, 只能向下向右移动, 现考虑网格中的障碍物, 从左上角走到右上角总共有多少条不同的路径
 *      (网格中的障碍物和空位置分别用 1 和 0 来表示)
 *
 * 难度: medium
 *
 * 思路: 动态规划: 状态转移方程, f(m, n) = f(m + 1, n) + f(m, n + 1), 其中f(1) = 0
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            if (obstacleGrid[i][n - 1] == 1) {
                break;
            }
            dp[i][n - 1] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (obstacleGrid[m - 1][i] == 1) {
                break;
            }
            dp[m - 1][i] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // f(m, n) = f(m + 1, n) + f(m, n + 1), 其中f(1) = 0
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }
}
