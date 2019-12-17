/**
 * @author qhhu
 * @date 2019/12/17 - 9:23
 *
 * [392] 判断子序列
 *
 * 题目: 给定字符串s和t, 判断s是否为t的子序列
 *       (字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串)
 *
 * 难度: easy
 *
 * 思路: 1. 双指针, s是t的子序列，即s中的所有字符都会按照顺序出现在t中
 *      2. 动态规划, f(i, j)表示长度为i的s的前缀串是否为长度为j的t的前缀串的子序列
 *         状态转移方程: f(i, j) = f(i - 1, j - 1) if cur chars same
 *                              = f(i, j - 1) if cur chars not same
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean isSubsequence1(String s, String t) {
        int indexS = 0, indexT = 0;
        while (indexS < s.length() && indexT < t.length()) {
            if (s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
                indexT++;
            } else {
                indexT++;
            }
        }

        return indexS == s.length();
    }

    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(m * n)
     */
    public boolean isSubsequence2(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1]; // dp[i][j]表示长度为i的s的前缀串是否为长度为j的t的前缀串的子序列, 1代表是
        // t为空串时
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 0;
        }
        // s为空串时(特殊情况s和t都为空串, dp[0][0] = 1)
        for (int j = 0; j <= tLen; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                // 当s[i-1] == t[j-1]时，s(i)与t(j)的匹配情况，取决于s(i-1)于t(j-1)的匹配情况
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                // 当s[i-1] != t[j-1]时，s(i)与t(j)的匹配情况，取决于s(i)于t(j-1)的匹配情况
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[sLen][tLen] == 1;
    }
}