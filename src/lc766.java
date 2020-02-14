/**
 * @author qhhu
 * @date 2020/2/14 - 8:53
 *
 * [766] 托普利茨矩阵
 *
 * 题目: 若矩阵的每一个方向有左上到右下的对角线上都具有相同元素, 则这个矩阵就是托普利茨矩阵. 判断给定m * n的矩阵是否是托普利茨矩阵.
 *
 * 难度: easy
 *
 * 思路: 遍历每条对角线, 判断当前对角线上的元素是否都相同.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = rows - 1; i >= 0; i--) {
            if (!check(matrix, matrix[i][0], i, 0)) {
                return false;
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (!check(matrix, matrix[0][j], 0, j)) {
                return false;
            }
        }

        return true;
    }

    private boolean check(int[][] matrix, int expectValue, int row, int col) {
        if (row >= matrix.length || col >= matrix[0].length) {
            return true;
        }
        if (matrix[row][col] != expectValue) {
            return false;
        }
        return check(matrix, expectValue, row + 1, col + 1);
    }

    /**
     * my solution
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        // 按照如下顺序遍历对角线
        // ^  ---------------------->
        // |  (0, 0)   (0, 1)   (0, 2)
        // |  (1, 0)   (1, 1)   (1, 2)
        // |  (2, 0)   (2, 1)   (2, 2)
        // |  (3, 0)   (3, 1)   (3, 2)

        int rows = matrix.length, cols = matrix[0].length;
        // 从左下角开始遍历
        int row = rows - 1, col = 0;
        while (col < cols) {
            // 遍历以当前节点为起点的对角线
            int i = row, j = col;
            int cur = matrix[i][j];
            while (i < rows && j < cols) {
                if (matrix[i++][j++] != cur) return false;
            }
            // 沿如上图顺序, 遍历每个对角线的起点
            if (row > 0) {
                row--;
            } else {
                col++;
            }
        }

        return true;
    }
}
