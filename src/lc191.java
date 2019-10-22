/*
 * @lc app=leetcode.cn id=191 lang=java
 *
 * [191] 位1的个数
 * 
 * 1、数一个数
 * 2、内建函数
 */
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1; // 不管原来的“符号”位的值，空上的全部直接填0
        }
        return res;
    }

    public int hammingWeight2(int n) {
        return Integer.bitCount(n);
    }
}

