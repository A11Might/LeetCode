/**
 * @author qhhu
 * @date 2019/11/28 - 23:36
 *
 * [1143] 最长公共子序列
 *
 * 题目: 给定两个字符串text1和text2, 返回这两个字符串的最长公共子序列
 *      (一个字符串的子序列是指这样一个新的字符串: 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
 *      （也可以不删除任何字符）后组成的新字符串)
 *
 * 难度: medium
 *
 * 思路: 动态规划, 状态转移方程: f(i, j) = f(i - 1, j - 1) if str1[i] == str2[j]
 *                                   or  max(f(i - 1, j), f(i, j - 1)) if str1[i] != str2[j]
 */
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        // dp[i][j]是text1.substring(0, i]和text2.substring(0, j]最长公共子序列的长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[len1][len2];
    }
}
