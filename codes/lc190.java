/*
 * @lc app=leetcode.cn id=190 lang=java
 *
 * [190] 颠倒二进制位
 * 
 * 位操作
 */
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            // (n >> i) & 1)将除第i位元素都置为0
            // << (31 - i)将第i为元素移到颠倒后的位置上
            res += ((n >> i) & 1) << (31 - i);
        }
        return res;
    }
}

