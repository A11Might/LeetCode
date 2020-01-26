import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author qhhu
 * @date 2020/1/26 - 13:23
 *
 * [5321] 阈值距离内邻居最少的城市
 *
 * 题目: 有n个城市, 按[0, n - 1]编号. 给定一个边数组edges, 其中edges[i] = [fromi, toi, weighti]代表fromi和toi
 *      两个城市之间的双向加权变, 距离阈值是一个整数distanceThreshold. 返回能通过某些路径到达其他城市数目最少, 且路径距离最
 *      大为distanceThreshold的城市
 *      (若多个城市符合题意, 返回编号最大的城市)
 *
 * 难度: medium
 *
 * 思路: 由边构建邻接表, 从每个城市开始bfs寻找符合题意的城市
 */
class Solution {

    List<Pair<Integer, Integer>>[] e = new List[100]; // 邻接表


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 构建邻接表
        for (int[] edge : edges) {
            if (e[edge[0]] == null) e[edge[0]] = new ArrayList<>();
            if (e[edge[1]] == null) e[edge[1]] = new ArrayList<>();
            e[edge[0]].add(new Pair<Integer, Integer>(edge[1], edge[2]));
            e[edge[1]].add(new Pair<Integer, Integer>(edge[0], edge[2]));
        }

        // 从每个城市开始bfs, 寻找符合题意的城市
        int ans = -1, adjacent = n + 1;
        for (int i = 0; i < n; i++) {
            int ret = bfs(i, distanceThreshold, n);
            if (ret <= adjacent) {
                // 当ret == adjacent时, 更新返回值(从小到大遍历城市, 当同时符合题意时, 当前城市的id较大)
                ans = i;
                adjacent = ret;
            }
        }

        return ans;
    }

    // 从给定城市开始bfs, 返回给定城市在阈值距离内的邻居城市数
    private int bfs(int id, int distanceThreshold, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int[] distances = new int[n]; // 记录每个城市到给定城市的距离
        queue.offer(id);
        visited[id] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int distance = distances[cur];
            if (e[cur] == null) return 0; // 当前城市没有相邻的城市, 相邻城市为0最少,直接返回
            for (Pair<Integer, Integer> pair : e[cur]) {
                int to = pair.getKey();
                int edge = pair.getValue();
                // 更新给定城市到当前城市的最短路径
                if (visited[to] && distances[to] < distance + edge) continue;
                distances[to] = distance + edge;
                queue.offer(to);
                visited[to] = true;
            }
        }

        // 找到给定城市在阈值距离内的邻居城市数
        int ret = 0;
        for (int distance : distances) {
            if (distance == 0) continue;
            if (distance <= distanceThreshold) ret++;
        }
        return ret;
    }
}