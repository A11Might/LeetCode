/**
 * @author qhhu
 * @date 2019/10/31 - 12:57
 *
 * [37] 解数独
 *
 * 题目: 编写一个程序, 通过已填充的空格来解决数独问题
 *
 * 难度: hard
 *
 * 思路: 回溯
 */
class Solution {
    /**
     * 时间复杂度: O(1) (9! ^ 9)
     * 空间复杂度: O(1)
     */
    private boolean[][] rowsUsed = new boolean[9][10]; // 记录某行, 某位数字是否已经被摆放(0 - 8一共9行, 1 - 9一共9个数)
    private boolean[][] colsUsed = new boolean[9][10]; // 记录某列, 某位数字是否已经被摆放
    private boolean[][] cubesUsed = new boolean[9][10]; // 记录某3 x 3宫格内, 某位数字是否已经被摆放
    public void solveSudoku(char[][] board) {
        // 对board进行预处理, 标记某行某列某宫格内已经出现的数字
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '0';
                rowsUsed[i][num] = true;
                colsUsed[j][num] = true;
                cubesUsed[cubeNum(i, j)][num] = true;
            }
        }
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int i, int j) {
        // 寻找为空的位置
        // 从左往右, 从上向下
        while (i < board.length && board[i][j] != '.') {
            i = j == 8 ? i + 1 : i;
            j = j == 8 ? 0 : j + 1;
        }
        // 已经遍历往所有位置
        if (i == 9) return true;
        // 在当前为空的位置尝试1 - 9
        for (int num = 1; num <= 9; num++) {
            if (rowsUsed[i][num] || colsUsed[j][num] || cubesUsed[cubeNum(i, j)][num]) continue;
            rowsUsed[i][num] = colsUsed[j][num] = cubesUsed[cubeNum(i, j)][num] = true;
            board[i][j] = (char) (num + '0');
            if (dfs(board, i, j)) return true;
            // 回溯
            rowsUsed[i][num] = colsUsed[j][num] = cubesUsed[cubeNum(i, j)][num] = false;
            board[i][j] = '.';
        }

        return false;
    }

    // 关键:
    // 计算当前位置在哪个宫格内
    // ../image/solution/lc036.jpg
    private int cubeNum(int i, int j) {
        int r = i / 3;
        int c = j / 3;
        return r * 3 + c;
    }
}