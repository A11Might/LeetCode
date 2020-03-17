/**
 * @author qhhu
 * @date 2019/10/28 - 22:21
 *
 * [130] 被围绕的区域
 *
 * 题目：在给定二维的矩阵中包含（'X'和'O'），找到所有被 'X' 围绕的区域中的 'O'，将其用 'X' 代替。
 *
 * 难度：medium
 *
 * 思路：Flood Fill（深度优先搜索）：逆向考虑问题，我们先统计出哪些 'O' 不会被替换，然后将其它 'O' 都变成 'X' 即可。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    private boolean[][] visited;
    private int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int rows = board.length, cols = board[0].length;
        visited = new boolean[rows][cols];

        // 枚举所有边界上的'O'，从该位置做 Flood Fill，即做深度优先遍历，只遍历是'O'的位置，并将所有遍历到的位置都标记成true。
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][cols - 1] == 'O') dfs(board, i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[rows - 1][j] == 'O') dfs(board, rows - 1, j);
        }

        // 将所有未遍历到的字符为 'O' 的位置变成'X'。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) board[i][j] = 'X';
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        visited[i][j] = true;
        for (int[] dir : direction) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
                    || board[x][y] == 'X' || visited[x][y]) continue;
            dfs(board, x, y);
        }
    }
}