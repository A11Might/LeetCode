/**
 * @author qhhu
 * @date 2019/12/21 - 9:16
 *
 * [72] 编辑距离
 *
 * 题目: 给定两个单词word1和word2, 返回将word1转换为word2的最少操作数
 *       (单词的操作有: 插入一个字符, 删除一个字符, 替换一个字符)
 *
 * 难度: hard
 *
 * 思路: 动态规划, dp[i][j]代表word1[0, i]和word2[0, j]的编辑距离, 操作的是word1
 *      状态转移方程: 当 word1[i] == word2[j]，编辑距离不变, 即dp[i][j] = dp[i-1][j-1]；
 *                   当 word1[i] != word2[j]，
 *                   word有三种操作: 替换, 即让word1[i] == word2[j], 所以dp[i][j] = dp[i-1][j-1] + 1
 *                                  删除, 删除word1[i], 所以dp[i][j] = dp[i - 1][j] + 1
 *                                  插入, 在word1[i]后插入与word2[j]相同的字符, 所以dp[i][j] = dp[i][j - 1] + 1
 *                   选取其中最小值, dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
 *
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // dp[i][j]代表word1[0, i]和word2[0, j]的编辑距离, 操作的是word1
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 当word2为空时, 只能从word1中删除字符
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        // 当word1为空时, 只能向word1中添加字符
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[len1][len2];
    }
}
