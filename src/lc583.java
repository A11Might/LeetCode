/**
 * @author qhhu
 * @date 2020/3/12 - 9:01
 *
 * [583] 两个字符串的删除操作
 *
 * 题目：给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * 难度：medium
 *
 * 思路：1. 为了求得最少删除次数，我们可以求出串 s1 和串 s2 最长公共子序列，我们记为 lcs。如果我们能求得 lcs 的值，我们可以轻易地求出答案
 *         为 m + n - 2 * lcs。这里 m 和 n 分别是给定字符串 s1 和 s2 的长度。
 *      2. 双序列型动态规划：给定两个字符串。
 *         状态表示：dp[i][j] 表示表示 word1 串前 i 个字符和 word2 串前 j 个字符匹配的最少删除次数.
 *         状态转移方程：              dp[i - 1][j - 1] if word1[i] == word2[j]
 *                    dp[i][j] =
 *                                  min(dp[i - 1][j] + 1, dp[i][j - 1] + 1) if word1[i] != word2[j]
 *         初始状态：两个空串的匹配的最少删除次数为 0，空串和非空的匹配的最少删除次数为非空串的长度。
 */
class Solution {
    /**
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 决策边界
        for (int i = 1; i <= len1; i++) dp[i][0] = i;
        for (int j = 1; j <= len2; j++) dp[0][j] = j;

        // 状态转移
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[len1][len2];
    }

}