import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 * 
 * 题目：找到若干个完全平方数使其和等于n，求完全平方数个数最少几个(任何数都可以由若干个完全平方数组成，1)
 *
 * 难度：medium
 * 
 * 思路：1、动态规划，f(n) = 1 + min{f(n-1^2), f(n-2^2), f(n-3^2), f(n-4^2), ... , f(n-k^2) //(k为满足k^2<=n的最大的k)}
 *      2、对问题建模，整个问题转化为一个图论问题，
 *         从n到0，每个数字表示一个节点，如果两个数字x到y相差一个完全平方数则连接一条边，就可以得到一个无权图，原问题转化为，求
 *         这个无权图中从n到0的最短路径
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                // f(i) = f(j * j) + f(i - j * j)
                min = Math.min(min, 1 + dp[i - (j * j)]);
            }
            dp[i] = min;
        }

        return dp[n];
    }

    public int numSquares2(int n) {
        if (n == 0) {
            return 0;
        }
        // 标记当前节点是否访问过
        boolean[] visited = new boolean[n + 1];
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        // 从n点出发寻找最短路径，当前步数为0
        queue.offer(new Pair<>(n, 0));
        visited[n] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int num = (int) cur.getKey();
            int step = (int) cur.getValue();

            // 向所有能走的节点前进
            for (int i = 1; num - i * i >= 0; i++) {
                // 若当前节点之前访问过，则跳过(若再走，步数一定大于之前访问的步数)
                if (!visited[num - i * i]) {
                    // 走到0点，返回步数
                    if (num - i * i == 0) return step + 1;
                    queue.offer(new Pair<>(num - i * i, step + 1));
                    visited[num - i * i] = true;
                }
            }
        }

        // 无解(其实肯定有解，1)
        throw new IllegalStateException("no solution");
    }
}

