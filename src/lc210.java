import java.util.*;

/**
 * @author qhhu
 * @date 2020/2/26 - 10:15
 *
 * [210] 课程表 II
 *
 * 题目: n 门课, 在选修某些课程之前需要一些先修课程, [0, 1]表示学习课程 0 需先完成课程 1, 返回学习所有课程的顺序.
 *
 * 难度: medium
 *
 * 思路: 1. 深度优先搜索: 后序遍历结果就是拓扑排序结果.
 *         不能使用先序遍历: 当前节点没有相邻节点时, 只能说明 dfs 走到了尽头, 即当前节点是拓扑排序最后面的节点, 不能说明当前节点可
 *         以先访问. 若使用先序遍历, 会将出度为 0, 入度不为 0 的节点放在序列的前面, 即先序遍历序列不是拓扑排序的结果.(important)
 *      2. 利用节点的入度进行拓扑排序: 首先处理入度为 0 的所有节点/课程(没有先修课程). 如果从图中删除所有这些课程, 以及它们的出边,
 *         就可以找到下一步应该处理的课程/节点. 这些节点也是入度为 0 的节点. 重复这样做, 直到所有的课程都被考虑在内.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    private int[] ret;
    private int index;

    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        ret = new int[numCourses];
        index = numCourses - 1;
        // 根据 prerequisites 构建图的临界表.
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]); // // [0, 1]表示 0 <- 1
        }

        // 遍历过程中标记访问过的节点, 用于避免重复访问同一个节点.
        boolean[] visited = new boolean[numCourses];
        // 标记当前递归链中访问过的节点, 用于判断当前递归链是否有环(当重复访问同一个节点时, 说明存在环).
        boolean[] curMarked = new boolean[numCourses];
        // 对 numCourses 个节点依次执行 dfs, 判断每个节点起步 dfs 是否存在环, 若存在环直接返回 false.
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, visited, curMarked, i)) {
                return new int[0];
            }
        }

        return ret;
    }

    private boolean hasCycle(List<Integer>[] graph, boolean[] visited, boolean[] curMarked, int curNode) {
        if (curMarked[curNode]) {
            return true;
        }
        if (visited[curNode]) {
            return false;
        }
        curMarked[curNode] = true;
        visited[curNode] = true;
        for (int nextNode : graph[curNode]) {
            if (hasCycle(graph, visited, curMarked, nextNode)) {
                return true;
            }
        }
        // 当前递归链中访问过的节点, 退出递归时清除标记.
        curMarked[curNode] = false;
        // 后序遍历.
        ret[index--] = curNode;
        return false;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        // 统计课程安排图中每个节点的入度.
        int[] inDegrees = new int[numCourses];
        for (int[] pre : prerequisites) { // [0, 1]表示 0 <- 1
            inDegrees[pre[0]]++;
        }

        // 将入度为 0 的节点加入队列(入度为 0 表示没有先修课程的课, 可以直接学习)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] ret = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            // 没有先修课程的课直接学习(先访问入度为 0 的节点).
            int curCourse = queue.poll();
            ret[index++] = curCourse;
            // 每个以当前课为先修课程的课, 先修课程数减一(当前节点访问完其对应节点入度减一).
            for (int[] pre : prerequisites) {
                if (pre[1] == curCourse) {
                    inDegrees[pre[0]]--;
                    // 先修课程数减至 0 的课加入队列, 可直接学习(入度为 0 的节点).
                    if (inDegrees[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }

        return index == numCourses ? ret : new int[0];
    }
}