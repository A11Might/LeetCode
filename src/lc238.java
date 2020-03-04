/*
 * @lc app=leetcode.cn id=238 lang=java
 *
 * [238] 除自身以外数组的乘积
 * 
 * 题目：给定长度为 n 的整数数组nums，其中 n > 1，返回输出数组 output，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *      （不能使用除法，使用常数空间复杂度和 O(n) 时间复杂度内完成）
 *
 * 难度：medium
 * 
 * 思路：output[i] = nums[0] * nums[i - 1] * 1 * nums[i + 1] + ... + nums[len - 1]
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ret = new int[len];
        ret[0] = 1;
        for (int i = 1; i < len; i++) {
            // 在返回值对应位置先存储当前元素左边的元素乘积
            ret[i] = ret[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = len - 2; i >= 0; i--) {
            // 在返回值对应位置再乘上当前元素右边的元素乘积
            temp *= nums[i + 1];
            ret[i] *= temp;
        }

        return ret;
    }
}