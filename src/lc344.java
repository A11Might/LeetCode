/*
 * @lc app=leetcode.cn id=344 lang=java
 *
 * [344] 反转字符串
 * 
 * 题目：将给定字符串反转
 * 
 * 难度：easy
 * 
 * 思路：使用首尾双指针，swap() 函数原地反转字符串
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;
        }
    }
}