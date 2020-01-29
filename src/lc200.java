/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿的个数
 *
 * 题目: 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量
 *
 * 难度: medium
 * 
 * 思路: dfs, floodfill算法, dfs模板题
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    private int rows, cols;
    private int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0; // 实例 [], grid[0].length数组越界
        rows = grid.length;
        cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islandsNum = 0; // 当前岛屿的数量
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0' || visited[i][j]) continue;
                islandsNum++;
                dfs(grid, visited, i, j);
            }
        }

        return islandsNum;
    }

    // 从grid[row][col]位置, 进行floodfill
    // 保证(row, col)合法为1, 且grid[row][col]是没有被访问过的陆地
    private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                grid[i][j] == '0' || visited[i][j]) return;
        visited[i][j] = true;
        for (int[] d : direction) {
            dfs(grid, visited, i + d[0], j + d[1]);
        }
    }
}