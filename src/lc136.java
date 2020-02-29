/*
 * @lc app=leetcode.cn id=136 lang=java
 *
 * [136] 只出现一次的数字
 *
 * 题目: 给定一个非空整数数组, 除了某个元素只出现一次以外, 其余每个元素均出现两次. 找出那个只出现了一次的元素.
 *
 * 难度: easy
 *
 * 思路: 两个相同的数异或的结果为 0, 对所有数进行异或操作, 最后的结果就是单独出现的那个数.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }

        return ret;
    }
}

