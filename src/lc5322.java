/**
 * @author qhhu
 * @date 2020/1/26 - 13:23
 *
 * [5322] 工作计划的最低难度
 *
 * 题目: 给定一个整数数组jobDifficulty和一个整数d, 分别表示工作难度和需要计划的天数. 第i项工作的难度是jobDifficulty[i],
 *      返回整个工作计划的最小难度. 若无法制定工作计划, 则返回-1
 *      (工作之间存在依赖, 若想执行第i项工作, 必须完成i之前的所有工作; 每天至少需要完成一项任务; 工作计划的总难度是这d天难度之和, 一天
 *      的工作难度是当前完成工作的最大难度)
 *
 * 难度: hard
 *
 * 思路: 动态规划, dp[i][j]表示第i天完成j个工作的最小难度
 *             状态转移方程, dp[i][j] = min(dp[i][j], dp[i - 1][k] + max(k + 1, j, jobDifficulty))
 */
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        int[][] dp = new int[d + 1][len + 1];
        for (int i = 0; i <= d; i++) {
            for (int j = 0; j <= len; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= d; i++) { // 第i天
            for (int j = 1; j <= len; j++) { // 完成j个工作
                int max = jobDifficulty[j - 1]; // max表示第i天完成的工作中难度最大的工作的难度
                for (int k = j - 1; k >= 0; k--) { // 第i - 1天完成k个工作
                    if (dp[i - 1][k] != -1) {
                        // i天完成j个工作的难度 = i - 1天完成[1, k]个工作的难度 + 第i天完成[k + 1, j]个工作的难度
                        if (dp[i][j] == -1 || dp[i][j] > max + dp[i - 1][k]) dp[i][j] = max + dp[i - 1][k];
                    }
                    // 实时更新第i天完成工作的最大难度
                    if (k > 0) max = Math.max(max, jobDifficulty[k - 1]);
                }
            }
        }

        return dp[d][len];
    }
}
