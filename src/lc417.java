import java.util.ArrayList;
import java.util.Arrays;
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
 * 思路: floodfill
 */
class Solution {
    // floodfill, 从四个边界开始dfs, 能到达的位置, 则为可以流向对应海洋的位置
    // 分别建立两个注册表Atlantic和Pacific进行dfs, 标记当前位置可以流向的海洋
    // 当Atlantic[i][j]和Pacific[i][j]同时为true时, 表示该位置可以同时到达Atlantic和Pacific
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix.length == 0) {
            return ans;
        }
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] Pacific = new boolean[rows][cols]; // 从太平洋出发的注册表
        boolean[][] Atlantic = new boolean[rows][cols]; // 从大西洋处罚的注册表
        // 遍历第一行和最后一行
        for (int i = 0; i < cols; i++) {
            dfs(matrix, 0, i, Pacific, matrix[0][i]);
            dfs(matrix, rows - 1, i, Atlantic, matrix[rows - 1][i]);
        }
        // 遍历第一列和最后一列
        for (int i = 0; i < rows; i++) {
            dfs(matrix, i, 0, Pacific, matrix[i][0]);
            dfs(matrix, i, cols - 1, Atlantic, matrix[i][cols - 1]);
        }
        // 当Atlantic[i][j]和Pacific[i][j]同时为true时, 表示该位置可以同时到达Atlantic和Pacific
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Pacific[i][j] && Atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    // floodfill
    private void dfs(int[][] matrix, int row, int col, boolean[][] visited, int pre) {
        if (row < 0 || matrix.length <= row || col < 0 || matrix[0].length <= col || visited[row][col] || matrix[row][col] < pre) {
            return;
        }
        visited[row][col] = true;
        dfs(matrix, row + 1, col, visited, matrix[row][col]);
        dfs(matrix, row - 1, col, visited, matrix[row][col]);
        dfs(matrix, row, col + 1, visited, matrix[row][col]);
        dfs(matrix, row, col - 1, visited, matrix[row][col]);
    }
}
