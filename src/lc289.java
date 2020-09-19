/**
 * [289] 生命游戏
 * 
 * 题目：根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。
 *      （要求使用 O(1) 的额外空间复杂度）
 * 
 * 难度：medium
 * 
 * 思路：每个位置上的 int 值只有 0 和 1 两种情况，所以我们可以使用 int 的倒数第二位来存储下一次更新的状态
 */
class Solution {
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 统计每个细胞周围的活细胞数
                int cnt = 0;
                for (int x = Math.max(0, i - 1); x <= Math.min(n - 1, i + 1); x++) {
                    for (int y = Math.max(0, j - 1); y <= Math.min(m - 1, j + 1); y++) {
                        if (x == i && y == j) continue;
                        if ((board[x][y] & 1) == 1) cnt++;
                    }
                }
                // 将该细胞更新的状态存储在倒数第二位上
                int next = 0;
                if ((board[i][j] & 1) == 1) {
                    if (cnt == 2 || cnt == 3) next = 1;
                } else {
                    if (cnt == 3) next = 1;
                }
                board[i][j] |= next << 1;
            }
        }
        // 更新细胞状态
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}