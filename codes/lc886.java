import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=886 lang=java
 *
 * [886] 可能的二分法
 * 
 * 题意：N个人分进任意大小的两组，有一组dislikes[i] = [a, b]，表示不允许将编号为a和b的人归入同一组，
 * 当可以用这种方法将每个人分进两组时，返回true；否则返回false
 * 
 * https://www.bilibili.com/video/av54580334
 * 思路：图的染色，c，将图染成两种颜色，可以则返回true，不可以返回false
 */
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> g = new HashMap<>(); // 邻接表式图
        builerGraph(g, dislikes); // 用dislikes[]数组建图

        int[] colors = new int[N + 1]; // 记录每个节点的颜色
        for (int i = 1; i < colors.length; ++i) {
            if (colors[i] == 0 && !dfs(g, i, 1, colors)) return false; // 当前节点未染色并且进行染色失败时，返回false
        }

        return true;
    }

    private boolean dfs(Map<Integer, Set<Integer>> g, int node, int color, int[] colors) {
        // 当前节点已有颜色，判断是否和应该染的颜色相同
        if (colors[node] != 0) {
            return colors[node] == color;
        }

        // 进行染色
        colors[node] = color;
        if (g.get(node) == null) return true;
        for (int next : g.get(node)) {
            if (!dfs(g, next, -color, colors)) return false;
        }

        return true;
    }

    // 用dislikes[]数组建图
    private void builerGraph(Map<Integer, Set<Integer>> g, int[][] dislikes) {
        for (int[] dislike : dislikes) {
            int a = dislike[0], b = dislike[1];
            g.putIfAbsent(a, new HashSet<>());
            g.putIfAbsent(b, new HashSet<>());
            g.get(a).add(b); // a讨厌b
            g.get(b).add(a); // b讨厌a
        }
    }
}

