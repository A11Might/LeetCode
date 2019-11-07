/**
 * @author qhhu
 * @date 2019/11/7 - 9:53
 *
 * [91] 解码方法
 *
 * 题目: 给定一个只包含数字的非空字符串，请计算解码方法的总数
 *      (一条包含字母 A-Z 的消息通过以下方式进行了编码: 'A' -> 1; 'B' -> 2 ... 'Z' -> 26)
 *
 * 难度: medium
 *
 * 思路: 动态规划, 状态转移方程f(i) = f(i + 1) + f(i + 2), f(i)为以i位置开头至字符串结尾的子串的解码总数
 */
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if (len == 1 || s.charAt(0) == '0') {
            return s.charAt(0) != '0'? 1 : 0;
        }
        int[] dp = new int[len + 1];
        dp[len] = 1; // 感觉这是关键(作为自底而上的底, 简化了代码)
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') { // '0'需要特殊考虑, '10'可以解码; '0'不可以解码
                // 将从i开始到结尾的子串的第一个数字单独解码
                dp[i] = dp[i + 1];
                // 将从i开始到结尾的子串的前两个数字单独解码
                if (i + 2 <= len && Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }
}
