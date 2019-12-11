/**
 * @author qhhu
 * @date 2019/12/11 - 9:36
 *
 * [304] 二维区域和检索 - 矩阵不可变
 *
 * 题目: 给定一个二维矩阵, 返回其子矩阵范围内元素的总和(该子矩阵的左上角为(row1, col1), 右下角为(row2, col2))
 *
 * 难度: medium
 *
 * 思路: 同303一维类似, sum(abcd)=sum(od)−sum(ob)−sum(oc)+sum(oa)
 */
class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return;
        }
        int cols = matrix[0].length;
        // 需要注意的是当需要考虑i - 1或j - 1是否小于零时, 我们应该多思考怎样简化代码, 将所有情况一般化, 如下
        // dp数组最外面一圈是0, 不需要考虑i - i或j - 1小于0的情况(very important)
        dp = new int[rows + 1][cols + 1];
        // dp[i][j] = matrix[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i + 1][j + 1] = matrix[i][j] + dp[i][j + 1] + dp[i + 1][j] - dp[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // ans = dp[i][j] - dp[i - 1][j] - dp[i][j - 1] + dp[i - 1][j - 1]
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
