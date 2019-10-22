/*
 * @lc app=leetcode.cn id=238 lang=java
 *
 * [238] 除自身以外数组的乘积
 * 
 * 题目：给定长度为n的整数数组nums，其中n > 1，返回输出数组output，其中output[i]等于nums中除nums[i] 之外其余各元素的乘积
 *      
 * 要求：不能使用除法，使用常数空间复杂度和O(n)时间复杂度内完成
 * 
 * 思路：output[i] = nums[i]左边的数乘积 * nums[i]右边的数乘积
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int curProduct = 1; // 当前位置左边或右边乘积的值，初始为1(最左边或最右边的位置没有左边的值或右边的值乘上1不改变其值)

        int[] res = new int[n];
        // 在返回值对应位置先存储当前元素左边的元素乘积
        for (int i = 0; i < n; ++i) {
            res[i] = curProduct;
            curProduct *= nums[i];
        }
        curProduct = 1;
        // 在返回值对应位置存储再乘上当前元素右边的元素乘积
        for (int i = n - 1; i >= 0; --i) {
            res[i] *= curProduct;
            curProduct *= nums[i];
        }

        return res;
    }
}

