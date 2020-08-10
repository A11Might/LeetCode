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
 * 思路：如果我们从环上的一个节点开始 DFS，那么那么本次遍历最终一定会再次访问自己，否则本次 DFS 中访问到的节点都可以排除在环上的可能。 
 *      那么，我们给 st 数组定义三种状态：0 表示当前节点未访问过；1 表示当前节点在本轮 DFS 中正在访问；2 表示当前节点已经访问过且排除在环上可能。
 *      在本轮 DFS 的时候将访问过的节点置 1，离开的时候将节点置 2 表示排除其在环上的可能。当 DFS 的过程中遇到 st 为 1 的节点时，就说明找到了一个环。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean canFinish(int n, int[][] pqs) {
        // 根据 prerequisites 构建图的临接表
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] pq : pqs) {
            int a = pq[0], b = pq[1]; // [0, 1] : 1 -> 0
            g[b].add(a);
        }
        
        int[] st = new int[n]; // 0：未访问过；1：当前递归中访问过；2：访问过
        for (int i = 0; i < n; i++) {
            if (st[i] == 0 && dfs(g, st, i)) return false;
        }
        return true;
    }

    // 检测有向图是否存在环。
    private boolean dfs(List<Integer>[] g, int[] st, int i) {
        st[i] = 1;
        for (int next : g[i]) {
            if (st[next] == 1) return true;
            if (st[next] == 2) continue;
            if (dfs(g, st, next)) return true;
        }
        st[i] = 2;
        return false;
    }
}