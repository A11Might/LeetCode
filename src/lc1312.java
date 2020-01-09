/**
 * @author qhhu
 * @date 2020/1/5 - 16:00
 *
 * [1312] 让字符串成为回文串的最少插入次数
 *
 * 题目: 给你一个字符串s, 每一次操作你都可以在字符串的任意位置插入任意字符, 返回让s成为回文串的最少操作次数
 *
 * 难度: hard
 *
 * 思路: 需要插入字符的位置是因为该位置字符与其对称位置字符不同, 需要插入字符(等效于删除当前字符)
 *       所以求出原字符串和倒序字符串的最长公共子序列(最大相同字符个数), 即为让s成为回文串的最少操作次数
 */
class Solution {
    public int minInsertions(String s) {
        // 状态转移方程
        // dp[a][b] = dp[a - 1][b - 1] + 1, if s.charAt(a) == s2.charAt(b)
        // dp[a][b] = max(dp[a - 1][b], dp[a][b - 1]), if s.charAt(a) != s2.charAt(b)
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        int longestCommentLen = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (s.charAt(i - 1) == s.charAt(len - j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                longestCommentLen = Math.max(dp[i][j], longestCommentLen);
            }
        }

        return s.length() - longestCommentLen;
    }
}
