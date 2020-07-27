/*
 * @lc app=leetcode.cn id=151 lang=java
 *
 * [151] 翻转字符串里的单词
 *
 * 题目：逐个翻转给定字符串中的每个单词
 *
 * 难度：medium
 *
 * 思路：以空格为分隔符取得每个单词，翻转单词
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public String reverseWords(String s) {
        if (s.length() == 0) return "";
        String[] strs = s.trim().split(" +"); // trim()去除首尾空格，" +"多个空格
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i] + " ");
        }
        return sb.toString().trim();
    }
}

