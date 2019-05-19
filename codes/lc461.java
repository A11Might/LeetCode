/*
 * @lc app=leetcode.cn id=461 lang=java
 *
 * [461] 汉明距离
 * 
 * 1、一位一位比较
 * 2、异或求后1的个数
 */
class Solution {
    public int hammingDistance(int x, int y) {
        if (x == y) {
            return 0;
        }
        int res = 0;
        while (x != y) {
            // 将除最后一位都置为0，然后比较最后一位
            if ((x & 1) != (y & 1)) {
                res++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return res;
    }

    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}

