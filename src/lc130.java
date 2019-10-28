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
 * 思路: dfs
 */
class Solution {
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

     // 从每一个边界的'O'开始dfs, 把遇到的'O'都标记上(这些'O'就是可以连通到边界的)
     // 然后遍历所有位置, 把没有标记过的'O'改变成'X'
     public void solve2(char[][] board) {
         if (board.length == 0) {
             return;
         }
         int rows = board.length, cols = board[0].length;
         boolean[][] flag = new boolean[rows][cols]; // 标记连通边界的'O'
         boolean[][] visited = new boolean[rows][cols];
         // 遍历第一行和最后一行
         for (int i = 0; i < cols; i++) {
             if (board[0][i] == 'O') {
                 dfs(board, 0, i, visited, flag);
             }
             if (board[rows - 1][i] == 'O') {
                 dfs(board, rows - 1, i, visited, flag);
             }
         }
         // 遍历第一列和最后一列
         for (int i = 0; i < rows; i++) {
             if (board[i][0] == 'O') {
                 dfs(board, i, 0, visited, flag);
             }
             if (board[i][cols - 1] == 'O') {
                 dfs(board, i, cols - 1, visited, flag);
             }
         }
         // 遍历所有位置, 把没有标记过的'O'改变成'X'
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 if (board[i][j] == 'O' && !flag[i][j]) {
                     board[i][j] = 'X';
                 }
             }
         }
     }

     private void dfs(char[][] board, int row, int col, boolean[][] visited, boolean[][] flag) {
         if (row < 0 || board.length <= row || col < 0 || board[0].length <= col) {
             return;
         }
         if (visited[row][col]) {
             return;
         }
         visited[row][col] = true;
         if (board[row][col] == 'X') {
             return;
         } else {
             flag[row][col] = true;
         }
         dfs(board, row + 1, col, visited, flag);
         dfs(board, row - 1, col, visited, flag);
         dfs(board, row, col + 1, visited, flag);
         dfs(board, row, col - 1, visited, flag);
     }

    // 并查集
    // https://leetcode-cn.com/problems/surrounded-regions/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-6/

    // bfs, bobo老师
    // https://github.com/liuyubobobo/Play-Leetcode/blob/master/0130-Surrounded-Regions/cpp-0130/main.cpp


}
