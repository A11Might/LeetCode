/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 * 
 * 中心拓展发：回文中心的两侧互为镜像。因此，回文可以从他的中心展开，并且只有2n-1个这样的中心(一个元素为中心的情况有n个，两个元素为中心的情况有n-1个)
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int end = 0;
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            int len1 = expendAroundCenter(s, i, i); // 以i为中心的回文子串长度
            int len2 = expendAroundCenter(s, i, i + 1); // 以i和i + 1为中心的回文子串长度
            res = Math.max(res, Math.max(len1, len2));
            if (res > end - start + 1) { // 当最长回文子串变化时，更新start和end
                start = i - (res - 1) / 2; // 起始位置奇偶不同
                end = i + res / 2; // 结束位置奇偶相同
            }
        }
        return s.substring(start, end + 1); // [start, end + 1)
    }

    // 以left和right为中心的最长回文子串
    private int expendAroundCenter(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 退出循环的条件为str.charAt(left) != str.charAt(right)，right - left + 1 -2去掉不构成回文子串的两个字符
    }
}
