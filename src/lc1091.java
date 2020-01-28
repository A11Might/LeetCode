import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author qhhu
 * @date 2020/1/28 - 10:41
 *
 * [1091] 二进制矩阵中的最短路径
 *
 * 题目: 给定一个n * n的网格, 每个单元格有两种状态: 空(0)或者阻塞(1). 返回从左上角到右下角的最短通常路径的长度, 若不存在则返回-1
 *      (路径中相邻单元格在八个方向之一连通)
 *
 * 难度: medium
 *
 * 思路: bfs(同树的层次遍历), bfs模板题
 */
class Solution {
    /**
     * 时间复杂度: O(n * n)
     * 空间复杂度: O(n * n)
     */
    int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int len = grid.length; // 方阵: len * len
        if (grid[0][0] == 1) return -1;
        boolean[][] visited = new boolean[len][len];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++; // 层数加一
            int size = queue.size(); // 当前层的节点数
            // 遍历当前层的所有节点
            while (size-- > 0) {
                int[] cur = queue.poll();
                // 若到达右下角, 则直接返回
                // 需放置在此处而不是下面for循环中(实例[[0]])
                // 正常情况应该放在for循环中(减少遍历的节点数)
                if (cur[0] == len - 1 && cur[1] == len - 1) {
                    return depth;
                }
                // 从当前位置向八个方向遍历
                for (int[] d : direction) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    // 排除: 1.越界 2.已访问 3.阻塞
                    if (x < 0 || x >= len || y < 0 || y >= len || visited[x][y] || grid[x][y] == 1) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }
}
