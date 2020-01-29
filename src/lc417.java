import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/29 - 21:54
 *
 * [417] 太平洋大西洋水流问题
 *
 * 题目: 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界
 *      规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动
 *      请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标
 *
 * 难度: medium
 *
 * 思路: dfs, floodfill算法, 题意水是从山上流到海里, 从高处流到大洋. 反过来想, 水从太平洋和大西洋里回到山上, 从低处回到高处.
 *           它们到能够到达的地方就是 `那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标`
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    private int rows, cols;
    private int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    // floodfill, 从四个边界开始dfs, 能到达的位置, 则为可以流向对应海洋的位置
    // 分别建立两个注册表pacific和atlantic进行dfs, 标记当前位置可以流向的海洋
    // 当pacific[i][j]和atlantic[i][j]同时为true时, 表示该位置可以同时到达pacific和atlantic
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return Collections.emptyList();
        rows = matrix.length;
        cols = matrix[0].length;
        boolean[][] pacific = new boolean[rows][cols]; // 从太平洋出发的注册表
        boolean[][] atlantic = new boolean[rows][cols]; // 从大西洋出发的注册表
        // 遍历第一列和最后一列
        for (int i = 0; i < rows; i++) {
            dfs(matrix, pacific, i, 0, matrix[i][0]);
            dfs(matrix, atlantic, i, cols - 1, matrix[i][cols - 1]);
        }
        // 遍历第一行和最后一行
        for (int j = 0; j < cols; j++) {
            dfs(matrix, pacific, 0, j, matrix[0][j]);
            dfs(matrix, atlantic, rows - 1, j, matrix[rows - 1][j]);
        }

        // 当pacific[i][j]和atlantic[i][j]同时为true时, 表示该位置可以同时到达pacific和atlantic
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) ans.add(Arrays.asList(i, j));
            }
        }
        return ans;
    }

    // floodfill
    private void dfs(int[][] matrix, boolean[][] visited, int i, int j, int pre) {
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                matrix[i][j] < pre || visited[i][j]) return;
        visited[i][j] = true;
        for (int[] d : direction) {
            dfs(matrix, visited, i + d[0], j + d[1], matrix[i][j]);
        }
    }
}