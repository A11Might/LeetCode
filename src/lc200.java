/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿的个数
 *
 * 题目: 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量
 *
 * 难度: medium
 * 
 * 思路: floodfill
 */
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid == null) { // 实例 [], grid[0].length数组越界
            return 0;
        }
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    ans++;
                    dfs(grid, visited, i, j);
                }
            }
        }

        return ans;
    }

    // 从grid[row][col]位置, 进行floodfill
    // 保证(row, col)合法, 且grid[row][col]是没有被访问过的陆地
    private void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        if (0 <= row && row < grid.length && 0 <= col && col < grid[0].length && grid[row][col] == '1' && !visited[row][col]) {
            visited[row][col] = true;
            dfs(grid, visited, row + 1, col);
            dfs(grid, visited, row - 1, col);
            dfs(grid, visited, row, col + 1);
            dfs(grid, visited, row, col - 1);
        }
    }
}