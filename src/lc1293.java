import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author qhhu
 * @date 2019/12/16 - 15:15
 *
 * [1293] 网格中的最短路径
 *
 * 题目: 给定一个m * n的网格, 每个单元格为0(空)或1(障碍物), 每一步可以从空白单元格向四个方向走, 你最多可以消除k个障碍物
 *       返回从左上角到右下角的最短路径; 若没有, 则返回-1
 *
 * 难度: hard
 *
 * 思路: bfs,
 */
class Solution {
    private int[][] dire = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][][] dist = new int[40][40][1600];

    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                for (int a = 0; a < 1600; a ++) {
                    dist[i][j][a] = -1;
                }
            }
        }
        Deque<Pair<Pair<Integer, Integer>, Integer>> queue = new ArrayDeque<>(); // ((x, y), distance)
        dist[0][0][0] = 0; // 从(0, 0)到达(x, y)且消除p个障碍物的路径长度
        queue.add(new Pair<>(new Pair<>(0, 0), 0));

        // bfs
        while (!queue.isEmpty()) {
            int x = queue.peek().getKey().getKey(), y = queue.peek().getKey().getValue(), p = queue.peek().getValue();
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int curX = x + dire[i][0], curY = y + dire[i][1];
                if (curX < 0 || curX >= rows || curY < 0 || curY >= cols) continue;
                int curP = p + grid[x][y]; // 更新消除的障碍
                if (curP <= k && dist[curX][curY][curP] == -1) { // 等于-1则未更新过说明未访问过(相当于visited)
                    dist[curX][curY][curP] = dist[x][y][p] + 1;
                    queue.add(new Pair<>(new Pair<>(curX, curY), curP));
                }
            }
        }

        // 在终点枚举消除0 - k个障碍物的路径, 从中找到最短路径
        int ans = -1;
        for (int i = 0; i <= k; i++) {
            if (dist[rows - 1][cols - 1][i] != -1) {
                if (ans == -1 || ans > dist[rows - 1][cols - 1][i]) ans = dist[rows - 1][cols - 1][i];
            }
        }

        return ans;
    }
}
