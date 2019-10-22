/*
 * @lc app=leetcode.cn id=73 lang=java
 *
 * [73] 矩阵置零
 * 
 * 1、病毒感染法 空间复杂度O(m*n)
 * 2、利用数组的首行和首列来记录需变零的行和列，从matrix[1][1]开始遍历记录
 */
class Solution {
    public void setZeroes1(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return;
        }
        // 遍历矩阵，标记0的位置
        int[][] flag = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == 0) {
                    flag[i][j] = 1;
                }
            }
        }
        // 矩阵置零
        for (int i = 0; i < flag.length; ++i) {
            for (int j = 0; j < flag[0].length; ++j) {
                if (flag[i][j] == 1) {
                    infect(matrix, i, j);
                }
            }
        }
    }

    // 将所在行和列的全部元素变为零
    private void infect(int[][] matrix, int i, int j) {
        infectRowLeft(matrix, i, j);
        infectRowRight(matrix, i, j);
        infectColUp(matrix, i, j);
        infectColDown(matrix, i, j);
    }

    private void infectRowLeft(int[][] matrix, int i, int j) {
        if (j >= 0) {
            matrix[i][j] = 0;
            infectRowLeft(matrix, i, j - 1);
        }
    }

    private void infectRowRight(int[][] matrix, int i, int j) {
        if (j < matrix[0].length) {
            matrix[i][j] = 0;
            infectRowRight(matrix, i, j + 1);
        }
    }

    private void infectColUp(int[][] matrix, int i, int j) {
        if (i >= 0) {
            matrix[i][j] = 0;
            infectColUp(matrix, i - 1, j);
        }
    }

    private void infectColDown(int[][] matrix, int i, int j) {
        if (i < matrix.length) {
            matrix[i][j] = 0;
            infectColDown(matrix, i + 1, j);
        }
    }

    public void setZeroes2(int[][] matrix) {
        // 遍历第一行，判断第一行是否需要变零
        boolean firstRow = false;
        for (int j = 0; j < matrix[0].length; ++j) {
            if (matrix[0][j] == 0) {
                firstRow = true;
                break;
            }
        }
        // 遍历第一列，判断第一列是否需要变零
        boolean firstCol = false;
        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        // 遍历除第一行第一列外的所有元素
        // 需要变零的行和列标记在第一行和第一列
        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[0].length; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 将第一行上的零所在列的元素全变为零
        for (int j = 1; j < matrix[0].length; ++j) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; ++i) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 将第一列上的零所在行的元素全变为零
        for (int i = 1; i < matrix.length; ++i) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; ++j) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 按需将第一行元素全变为零
        if (firstRow) {
            for (int j = 0; j < matrix[0].length; ++j) {
                matrix[0][j] = 0;
            }
        }
        // 按需将第一列元素全变为零
        if (firstCol) {
            for (int i = 0; i < matrix.length; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}

