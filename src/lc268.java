/*
 * @lc app=leetcode.cn id=268 lang=java
 *
 * [268] 缺失数字
 *
 * 题目: 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列, 找出 0 .. n 中没有出现在序列中的那个数.
 *
 * 难度: easy
 *
 * 思路: 同 136. 只出现一次的数字, 从 0 异或到 n, 再使用异或和依次异或数组中的数字, 最终剩下的就是没有出现的数.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int missingNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i <= nums.length; i++) {
            ret ^= i;
        }
        for (int num : nums) {
            ret ^= num;
        }

        return ret;
    }
}

