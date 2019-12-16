/**
 * @author qhhu
 * @date 2019/12/16 - 14:57
 *
 * [1292] 元素和小于等于阈值的正方形的最大边长
 *
 * 题目: 给定一个矩阵和一个整数阈值, 返回矩阵中元素总和小于或等于阈值的正方形区域的最大边长, 如果没有, 则返回0
 *
 * 难度: medium
 *
 * 思路: 二维前缀和(原来这东西还有名字), 枚举所有正方形的面积
 */
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length, cols = mat[0].length;
        int[][] dp = new int[rows + 1][cols + 1]; // dp数组行列各加一维, 方便计算
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                dp[i][j] = mat[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        int ans = 0;
        // 枚举所有正方形, (i - 1, j - 1)为正方形的右下角, k为正方形边长
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                for (int k = 1; i - k >= 0 && j - k >= 0; k++) {
                    if (dp[i][j] + dp[i - k][j - k] - dp[i - k][j] - dp[i][j - k] <= threshold) {
                        ans = Math.max(ans, k);
                    }
                }
            }
        }

        return ans;
    }
}
