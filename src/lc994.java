import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author qhhu
 * @date 2020/3/4 - 9:03
 *
 * [994] 腐烂的橘子
 *
 * 题目：在给定的网格中，每个单元格可以有以下三个值之一：值 0 代表空单元格；值 1 代表新鲜橘子；值 2 代表腐烂的橘子。每分钟，任何与腐烂的橘子
 *      （在 4 个正方向上）相邻的新鲜橘子都会腐烂。返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 *
 * 难度： easy
 *
 * 思路：多源广度优先搜索，对于所有的腐烂橘子，其实它们在广度优先搜索上是等价于同一层的节点的，将这些腐烂橘子都放进队列里进行广度优先搜索即可。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    private int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        // 将腐烂橘子都放进队列里进行广度优先搜索。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 进行广度优先搜索。
        int depth = -1;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int[] dir : direction) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x < 0 || x >= rows || y < 0 || y >= cols
                            || grid[x][y] != 1 || visited[x][y]) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }

        // 判断是否全部橘子都腐烂了。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    return -1;
                }
            }
        }

        // 若没有橘子则返回 0。
        return depth == -1 ? 0 : depth;
    }
}