/*
 * @lc app=leetcode.cn id=461 lang=java
 *
 * [461] 汉明距离
 *
 * 题目: 返回两个整数之间的汉明距离.
 *      (两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目.)
 *
 * 难度: easy
 *
 * 思路: 对两个数进行异或操作, 位级表示不同的那一位为 1, 统计有多少个 1 即可.
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public int hammingDistance(int x, int y) {
        int ret = x ^ y;
        return Integer.bitCount(ret);
    }
}
