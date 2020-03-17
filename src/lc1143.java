/**
 * @author qhhu
 * @date 2019/11/28 - 23:36
 *
 * [1143] 最长公共子序列
 *
 * 题目：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 *      (一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
 *      （也可以不删除任何字符）后组成的新字符串)
 *
 * 难度：medium
 *
 * 思路：双序列型动态规划：给定两个字符串。
 *      状态表示：dp[i][j] 表示字符串 text1[0, i - 1]，text2[0, j - 1] 的最长公共子序列的长度。
 *      状态转移方程：             = dp[i - 1][j - 1] + 1, S1i == S2j
 *                  dp[i][j]
 *                               = max(dp[i - 1][j], dp[i][j - 1], S1i != S2j
 *      初始状态：当一个字符串长度为 0 时，两字符串的最长公共子序列的长度为 0。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // 决策边界：第一行第一列。

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}