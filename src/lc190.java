/*
 * @lc app=leetcode.cn id=190 lang=java
 *
 * [190] 颠倒二进制位
 *
 * 题目: 颠倒给定的 32 位无符号整数的二进制位.
 *
 * 难度: easy
 *
 * 思路: 像读取字符串中的整数一样, 一位一位的读取给定整数的二进制数来颠倒整数.
 */
public class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret <<= 1;
            ret |= (n & 1);
            n >>>= 1;
        }

        return ret;
    }
}

