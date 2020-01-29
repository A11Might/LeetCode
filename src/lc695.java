/**
 * @author qhhu
 * @date 2020/1/29 - 9:56
 *
 * [695] 岛屿的最大面积
 *
 * 题目: 给定一个只包含0和1的非空二维数组grid, 一个岛屿是由四个方向(水平或者垂直)的1(代表土地)构成的组合. 假设二维矩阵的四个边缘都被水包围着.
 *      返回给定二维数组中最大的岛屿面积(若没有岛屿, 则返回面积为0)
 *
 * 难度: medium
 *
 * 思路: dfs, floodfill算法同lc200, dfs模板题
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    private int rows, cols;
    private int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        rows = grid.length;
        cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxArea = 0; // 当前最大的岛屿面积
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxArea = Math.max(maxArea, dfs(grid, visited, i, j));
            }
        }

        return maxArea;
    }

    // 从grid[row][col]位置进行floodfill, 同时记录遍历过得格子数量(即岛屿面积)
    // 保证(row, col)合法为1, 且grid[row][col]是没有被访问过的陆地
    private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                grid[i][j] == 0 || visited[i][j]) return 0;
        visited[i][j] = true;
        int area = 1;
        for (int[] d : direction) {
            area += dfs(grid,visited, i + d[0], j + d[1]);
        }

        return area;
    }
}
