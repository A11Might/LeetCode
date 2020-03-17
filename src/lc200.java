/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿的个数
 *
 * 题目：给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 *
 * 难度：medium
 * 
 * 思路：Flood Fill（深度优先搜素）：遍历矩阵，若当前点为陆地，则深度优先搜索遍历所有与之相连的陆地，来标记整个岛屿。在遍历过程中累计岛屿数量。
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    private boolean[][] visited;
    private int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        visited = new boolean[rows][cols];
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    cnt++;
                    dfs(grid, i, j);
                }
            }
        }

        return cnt;
    }

    private void dfs(char[][] grid, int i, int j) {
        visited[i][j] = true;
        for (int[] dir : direction) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
                    || grid[x][y] == '0' || visited[x][y]) continue;
            dfs(grid, x, y);
        }
    }
}