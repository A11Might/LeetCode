/**
 * @author qhhu
 * @date 2019/10/31 - 12:57
 *
 * [37] 解数独
 *
 * 题目: 编写一个程序，通过已填充的空格来解决数独问题
 *
 * 难度: hard
 *
 * 思路: 回溯
 */
class Solution {
    public void solveSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] rows = new boolean[9][10]; // 0-8一共9行, 1-9一共9个数
        // 记录某列，某位数字是否已经被摆放
        boolean[][] columns = new boolean[9][10];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        // ./image/lc036.jpg
        boolean[][] boxes = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rows[i][board[i][j] - '0'] = true;
                    columns[j][board[i][j] - '0'] = true;
                    boxes[i / 3 * 3 + j / 3][board[i][j] - '0'] = true;
                }
            }
        }

        dfs(board, 0, 0, rows, columns, boxes);
    }

    private boolean dfs(char[][] board, int row, int col, boolean[][] rows, boolean[][] columns, boolean[][] boxes) {
        // 找寻空位置
        while (board[row][col] != '.') {
            if (col == 8) {
                row++;
                col = 0;
            } else {
                col++;
            }
            if (row == 9) {
                return true;
            }
        }
        // 尝试1-9
        for (int i = 1; i <= 9; i++) {
            int boxIndex = row / 3 * 3 + col / 3;
            if (!rows[row][i] && !columns[col][i] && !boxes[boxIndex][i]) {
                board[row][col] = (char) ('0' + i);
                rows[row][i] = true;
                columns[col][i] = true;
                boxes[boxIndex][i] = true;
                if (dfs(board, row, col, rows, columns, boxes)) {
                    return true;
                }
                // 回溯
                board[row][col] = '.';
                rows[row][i] = false;
                columns[col][i] = false;
                boxes[boxIndex][i] = false;
            }
        }

        return false;
    }
}
