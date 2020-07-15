import java.util.List;

/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * 题目：找出给定三角形自顶向下的最小路径和
 *      （每一步只能移动到下一行中相邻的节点上）
 *
 * 难度：medium
 * 
 * 思路：动态规划，点 (i, j) 的下一行的相邻数字是 (i + 1, j) 和 (i + 1, j + 1)。
 *              f(i, j) 表示从下往上走到位置 (i, j) 时的最小路径和，状态转移方程是 f(i, j) = (i,j) + min(f(i + 1,j),f(i + 1,j + 1))
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 重复使用一维数组，记录每一层的最小值
        int[] dp = new int[n + 1];
        // 自下而上寻找最小路径和
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> prelist = triangle.get(i);
            for (int j = 0; j < prelist.size(); j++) {
                // 这里的dp[j]使用的时候是上一层的，赋值之后变成当前层
                dp[j] = Math.min(dp[j] + prelist.get(j), dp[j + 1] + prelist.get(j));
            }
        }
        return dp[0];
    }
}