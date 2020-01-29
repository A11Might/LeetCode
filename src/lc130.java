/**
 * @author qhhu
 * @date 2019/10/28 - 22:21
 *
 * [130] 被围绕的区域
 *
 * 题目: 在给定二维的矩阵中(包含'X'和'O'), 找到所有被'X'围绕的区域中的'O', 将其用'X'代替
 *
 * 难度: medium
 *
 * 思路: dfs, floodfill算法, 从矩阵的四个边上的节点开始进行dfs, 这样遍历到的'O'即为未被'X'围绕的, 剩下的即为围绕的'O'
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    private int rows, cols;
    private int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    // 从每一个边界的'O'开始dfs, 把遇到的'O'都标记上(这些'O'就是可以连通到边界的)
    // 然后遍历所有位置, 把没有标记过的'O'改变成'X'
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        rows = board.length;
        cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        // 遍历第一列和最后一列
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfs(board, visited, i, 0);
            if (board[i][cols - 1] == 'O') dfs(board, visited, i, cols - 1);
        }
        // 遍历第一行和最后一行
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') dfs(board, visited, 0, j);
            if (board[rows - 1][j] == 'O') dfs(board, visited, rows - 1, j);
        }
        // 遍历所有位置, 把没有标记过的'O'改变成'X'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) board[i][j] = 'X';
            }
        }
    }

    // floodfill
    private void dfs(char[][] board, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                board[i][j] == 'X' || visited[i][j]) return;
        visited[i][j] = true;
        for (int[] d : direction) {
            dfs(board, visited, i + d[0], j + d[1]);
        }
    }

    // 硬dfs
    // 在每一个位置进行dfs
    // dfs, 超时最后两个case过不了
    // 从每个'O'开始做dfs, 如果遍历完成后没有到达边界的'O', 则把当前'O'改为'X'
    // 如果遍历过程中到达边界的'O', 直接结束dfs, 当前的'O'不用操作
    // 不能在dfs过程中, 把当前'O'改为'X', 因为无法判断当前'O'是否符合要求(卡在这了, 要变通妈的)
    public void solve1(char[][] board) {
     if (board.length == 0) { // 实例: []
         return;
     }
     int rows = board.length, cols = board[0].length;
     for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
             boolean[][] visited = new boolean[rows][cols];
             // 从每个'O'开始做dfs, 如果遍历完成后没有到达边界的'O', 则把当前'O'改为'X'
             if (board[i][j] == 'O' && dfs(board, i, j, visited)) {
                 board[i][j] = 'X';
             }
         }
     }
    }

    private boolean dfs(char[][] board, int row, int col, boolean[][] visited) {
     if ((row < 0 || board.length <= row || col < 0 || board[0].length <= col)) {
         return false;
     }
     // 关键, 访问过的位置暂且认为它是不连通边界的
     // 最终若连通边界的位置一定返回false
     if (visited[row][col]) {
         return true;
     }
     visited[row][col] = true;
     if (board[row][col] == 'X') {
         return true;
     }
     return dfs(board, row + 1, col, visited) &&
            dfs(board, row - 1, col, visited) &&
            dfs(board, row, col + 1, visited) &&
            dfs(board, row, col - 1, visited);

    }

    // 并查集
    // https://leetcode-cn.com/problems/surrounded-regions/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-6/

    // bfs, bobo老师
    // https://github.com/liuyubobobo/Play-Leetcode/blob/master/0130-Surrounded-Regions/cpp-0130/main.cpp


}
