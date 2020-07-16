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
 * 思路：Flood Fill：逆向考虑问题，我们先统计出哪些 'O' 不会被替换，具体的从四个边界上的 'O' 开始 Flood Fill 来标记所有没有被包围的 'O'，
 *                  然后遍历整个矩阵，将未标记的 'O' 转化为 'X'。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    private int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        if (board.length == 0) return;
        int n = board.length, m = board[0].length;
        boolean[][] st = new boolean[n][m];
        // 具体的从四个边界上的 'O' 开始 Flood Fill 来标记所有没有被包围的 'O'
        for (int i = 0; i < n; i++) {
            dfs(board, st, i, 0);
            dfs(board, st, i, m - 1);
        }
        for (int j = 0; j < m; j++) {
            dfs(board, st, 0, j);
            dfs(board, st, n - 1, j);
        }
        // 将所有未遍历到的字符为 'O' 的位置变成'X'。
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && !st[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, boolean[][] st, int i, int j) {
        if (board[i][j] != 'O') return;
        st[i][j] = true;
        for (int[] d : dir) {
            int x = i + d[0], y = j + d[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
                    || st[x][y]) continue;
            dfs(board, st, x, y);
        }
    }
}