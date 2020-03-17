import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author qhhu
 * @date 2020/3/17 - 15:01
 *
 * [542] 01 矩阵
 *
 * 题目：给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。两个相邻元素键的距离为 1.
 *
 * 难度：medium
 *
 * 思路：求从点 0 到 1 的最短路。由于边权都相等，所以可以用 BFS 求最短路。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    private int[][] dist;
    private int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[][] {};
        int rows = matrix.length, cols = matrix[0].length;
        dist = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        // 多源广度优先搜索：初始时将所有 0 元素的坐标放入队，
        // 初始化距离矩阵：0 元素位置的值为 0，其余为 -1。也可以当做 visited 使用。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : direction) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x < 0 || x >= rows || y < 0 || y >= cols
                        || matrix[x][y] == 0 || dist[x][y] != -1) continue;
                queue.offer(new int[] {x, y});
                dist[x][y] = dist[cur[0]][cur[1]] + 1;
            }
        }

        return dist;
    }
}