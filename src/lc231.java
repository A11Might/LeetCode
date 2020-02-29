/*
 * @lc app=leetcode.cn id=231 lang=java
 *
 * [231] 2 的幂
 * 
 * 题目: 判断给定整数是否是 2 的幂.
 *
 * 难度: easy
 *
 * 思路: 2 的幂次方在二进制下, 有且只有一位是 1.
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public boolean isPowerOfTwo(int n) {
        // num & (num - 1) 操作将二进制中的最后一个 1 去掉.
        return n > 0 && (n & (n - 1)) == 0;
    }
}