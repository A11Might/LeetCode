import java.util.*;

/**
 * @author qhhu
 * @date 2020/2/26 - 10:15
 *
 * [210] 课程表 II
 *
 * 题目：n 门课，在选修某些课程之前需要一些先修课程，[0, 1] 表示学习课程 0 需先完成课程 1，返回学习所有课程的顺序。
 *
 * 难度：medium
 *
 * 思路：拓扑排序，首先处理入度为 0 的所有节点（没有先修课程）。如果从图中删除所有这些课程，以及它们的出边，
 *       就可以找到下一步应该处理的节点，这些节点也是入度为 0 的节点，重复这样做，直到所有的课程都被考虑在内。
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] findOrder(int n, int[][] prs) {
        List<Integer>[] g = new List[n];
        int[] degree = new int[n]; // 统计课程安排图中每个节点的入度
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] pr : prs) {
            g[pr[1]].add(pr[0]); // [0, 1]: 1 -> 0
            degree[pr[0]]++;
        }
        
        // 将入度为 0 的节点加入队列（入度为 0 表示没有先修课程的课, 可以直接学习）
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) queue.offer(i);
        }
        int[] ans = new int[n];
        int idx = 0;
        while (!queue.isEmpty()) {
            // 没有先修课程的课直接学习（先访问入度为 0 的节点）
            int cur = queue.poll();
            ans[idx++] = cur;
            // 每个以当前课为先修课程的课，先修课程数减一（当前节点访问完其对应节点入度减一）
            for (int next : g[cur]) {
                degree[next]--;
                // 先修课程数减至 0 的课加入队列，可直接学习（入度为 0 的节点）
                if (degree[next] == 0) queue.offer(next);
            }
        }
        
        return idx == n ? ans : new int[0];
    }
}