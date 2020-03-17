/**
 * @author qhhu
 * @date 2019/12/21 - 9:16
 *
 * [72] 编辑距离
 *
 * 题目：给定两个单词 word1 和 word2，返回将 word1 转换为 word2 的最少操作数。
 *     （单词的操作有：插入一个字符，删除一个字符，替换一个字符）
 *
 * 难度：hard
 *
 * 思路：双序列型动态规划：给定两个字符串。
 *      状态表示：dp[i][j] 表示 word1[0, i] 和 word2[0, j] 的编辑距离，操作的是 word1。
 *      状态转移方程：           dp[i-1][j-1] if word1[i] == word2[j]
 *                 dp[i][j] =  min(                  if word1[i] != word2[j]
 *                                 dp[i][j - 1] + 1, 插入，在 word1[i] 后插入与 word2[j] 相同的字符
 *                                 dp[i - 1][j] + 1, 删除，删除 word1[i]
 *                                 dp[i-1][j-1] + 1) 替换，让 word1[i] == word2[j]
 *      初始状态；两个空串的编辑距离为 0，空串和非空的匹配的编辑距离为非空串的长度。
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
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
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1,
                            Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                }
            }
        }

        return dp[len1][len2];
    }
}