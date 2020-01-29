import java.util.ArrayDeque;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 * 
 * 题目: 找到若干个完全平方数使其和等于n, 求完全平方数个数最少几个(任何数都可以由若干个完全平方数组成, 1)
 *
 * 难度: medium
 * 
 * 思路: 1. 动态规划,
 *         状态转移方程: f(n) = 1 + min{f(n-1^2), f(n-2^2), f(n-3^2), f(n-4^2), ... , f(n-k^2) //(k为满足k^2<=n的最大的k)}
 *      2. 对问题建模, 整个问题转化为一个图论问题,
 *                    n = 7
 *                  6       3
 *               5    2     2
 *              4 1   1     1
 *              0 0   0     0
 *          n代表一个节点, 与之相连的是相差一个完全平方数的数, 这样就可以得到一个无权图
 *          将原问题转化为: 求这个无权图中从n到0的最短路径, bfs模板题
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 一个数组多有i个1组成
            for (int j = 1; j * j <= i; j++) {
                // f(i) = f(i - j * j) + f(j * j)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int numSquares2(int n) {
        if (n == 0) return 0;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1]; // 标记当前节点是否访问过
        // 从n点出发寻找最短路径, 当前步数为0
        queue.add(n);
        visited[n] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++; // 步数加一
            int size = queue.size(); // 上一层的节点数
            // 以上一层遍历到的节点作为起点
            while (size-- > 0) {
                int cur = queue.poll();
                // 遍历其能访问到的所有节点
                for (int i = 1; i * i <= cur; i++) {
                    int next = cur - i * i;
                    // 走到节点0找到最短路径, 返回步数
                    if (next == 0) return depth;
                    // 若当前节点之前访问过, 则跳过(若再走, 步数一定大于之前访问的步数)
                    if (visited[next]) continue;
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        // n的组成除1外无其他完全平方数
        return n;
    }
}

