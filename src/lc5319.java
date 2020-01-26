/**
 * @author qhhu
 * @date 2020/1/26 - 13:23
 *
 * [5319] 删除回文子序列
 *
 * 题目: 给定一个仅由'a'和'b'组成的字符串s, 每次删除操作都可以从s中删除一个回文子序列, 返回删除给定字符串中所有字符的最小删除次数
 *      (子序列不连续)
 *
 * 难度: easy
 *
 * 思路: 只包含两种字母, 最多删除两次
 */
class Solution {
    public int removePalindromeSub(String s) {
        int len = s.length();
        if (len == 0) return 0; // 字符串为空, 零次删除
        int lo = 0, hi = len - 1;
        while (lo < hi) {
            // 若s不为回文字符串, 两次删除
            if (s.charAt(lo++) != s.charAt(hi--)) return 2;
        }

        // 若s为回文字符串, 一次删除
        return 1;
    }
}
