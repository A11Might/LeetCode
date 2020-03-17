import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 * 
 * 题目：找到若干个完全平方数使其和等于 n，求完全平方数个数最少几个（任何数都可以由若干个完全平方数组成，1）。
 *
 * 难度：medium
 * 
 * 思路：1. 划分型动态规划：将整数 n 划分为 k 段。
 *         状态表示：dp[i] 表示将整数 i 划分的最小段数。
 *         状态转移方程：题意说将 n 划分为若干个完全平方数，dp[n] = min(1 + dp[n - 1], 1 + dp[n - 4], ...)。
 *         初始状态：整数 1 划分的最大段数为 1。
 *      2. 对问题建模，整个问题转化为一个图论问题。
 *                    n = 7
 *                  6       3
 *               5    2     2
 *              4 1   1     1
 *              0 0   0     0
 *          n 代表一个节点，与之相连的是相差一个完全平方数的数，这样就可以得到一个无权图。
 *          将原问题转化为：求这个无权图中从 n 到 0 的最短路径。
 */
class Solution {
    /**
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int numSquares1(int n) {
        int[] dp = new int[n + 1];

        // 决策状态
        dp[1] = 1;

        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j += 1) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }

        return dp[n];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int numSquares2(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] dist = new int[n + 1];
        // 初始化为 n + 1，方便后面更新，实例 [1]。
        Arrays.fill(dist, n + 1);
        queue.offer(n);
        dist[n] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == 0) return dist[cur];
            for (int i = 1; i * i <= cur; i++) {
                int next = cur - i * i;
                // 因为节点可能被重复访问，所以如果 next 距离能被更新，就更新（important）。
                if (dist[next] > dist[cur] + 1) {
                    queue.offer(next);
                    dist[next] = dist[cur] + 1;
                }
            }
        }

        return 0;
    }
}

