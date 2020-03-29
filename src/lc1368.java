import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author qhhu
 * @date 2020/3/19 - 15:27
 *
 * [1368] 使网格图至少有一条有效路径的最小代价
 *
 * 难度：hard
 *
 * 思路：抽象成边权为 0 或 1 的图，使用 0 - 1 BFS（特殊的 dijkstra 算法）。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    public int minCost(int[][] grid) {
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.addFirst(new int[] {0, 0});
        dist[0][0] = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int i = cur[0], j = cur[1];

            // 判断当前节点是否被拓展过，拓展过则无需拓展，来优化时间复杂度。
            // 因为由 dijkstra 算法可知，在非负边权的图中，第一次弹出即为最小值，之后不可能更小了。
            if (visited[i][j]) continue;
            visited[i][j] = true;

            for (int k = 0; k < 4; k++) {
                int x = i + dir[k][0], y = j + dir[k][1];
                if (x < 0 || x >= rows || y < 0 || y >= cols) continue;

                int cost = grid[i][j] - 1 == k ? 0 : 1;
                if (dist[i][j] + cost < dist[x][y]) {
                    dist[x][y] = dist[i][j] + cost;

                    // 使用双端队列，边权为 0 时，从前面插入队列；边权为 1 时从后面插入队列。
                    if (cost == 0) {
                        dq.addFirst(new int[] {x, y});
                    } else {
                        dq.addLast(new int[] {x, y});
                    }
                }
            }
        }

        return dist[rows - 1][cols -1];
    }
}