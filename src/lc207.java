import java.util.*;

/*
 * @lc app=leetcode.cn id=207 lang=java
 *
 * [207] 课程表
 * 
 * 题目：n 门课，在选修某些课程之前需要一些先修课程，[0, 1] 表示学习课程 0 需先完成课程 1，判断是否可能完成所有课程的学习。
 *
 * 难度：medium
 * 
 * 思路：1. 利用结点的入度进行拓扑排序: 首先处理入度为 0 的所有节点/课程(没有先修课程). 如果从图中删除所有这些课程, 以及它们的出边,
 *         就可以找到下一步应该处理的课程/节点. 这些节点也是入度为 0 的节点. 重复这样做, 直到所有的课程都被考虑在内.
 *      2. 检测有向图是否存在环：如果我们从环上的一个节点开始 DFS，那么那么本次遍历最终一定会再次访问自己，否则本次 DFS 中访问到的节
 *         点都可以排除在环上的可能。 那么，我们给 visited 数组定义三种状态：-1 表示当前节点未访问过；0 表示当前节点在本轮 DFS 中正
 *         在访问；1 表示当前节点已经访问过且排除在环上可能。在本轮 DFS 的时候将未访问过的节点置 0，离开的时候将节点置 1 表示排除其在
 *         环上的可能。当 DFS 的过程中遇到 visited 为 0 的节点时，就说明找到了一个环。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 统计课程安排构成的图中每个节点的入度。
        int[] inDegrees = new int[numCourses];
        for (int[] pre : prerequisites) inDegrees[pre[0]]++;

        // 将入度为 0 的节点加入队列（入度为 0 表示没有先修课程的课，可以直接学习）
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // 计算实际可以上几节课。
        int cnt = 0;
        while (!queue.isEmpty()) {
            // 没有先修课程的课直接学习（先访问入度为 0 的节点）。
            int curCourse = queue.poll();
            cnt++;
            // 每个以当前课为先修课程的课，先修课程数减一（当前节点访问完其对应节点入度减一）。
            for (int[] pre : prerequisites) {
                if (pre[1] == curCourse) {
                    inDegrees[pre[0]]--;
                    // 先修课程数减至 0 的课加入队列，可直接学习（入度为 0 的节点）。
                    if (inDegrees[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }

        return cnt == numCourses;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private List<Integer>[] graph;
    private int[] visited;

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 根据 prerequisites 构建图的临接表。
        graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] pre : prerequisites) graph[pre[1]].add(pre[0]);

        visited = new int[numCourses];
        Arrays.fill(visited, -1);
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == -1 && dfs(i)) return false;
        }

        return true;
    }

    // 检测有向图是否存在环。
    private boolean dfs(int curNode) {
        if (visited[curNode] == 0) return true;
        if (visited[curNode] == 1) return false;
        visited[curNode] = 0;
        for (int nextNode : graph[curNode]) {
            if (dfs(nextNode)) return true;
        }
        visited[curNode] = 1;
        return false;
    }
}

