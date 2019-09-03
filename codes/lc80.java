/*
 * @lc app=leetcode.cn id=80 lang=java
 *
 * [80] 删除排序数组中的重复项 II
 * 
 * 题目：删除给定数组中的重复次数超过两次的元素
 *      (使得每个元素最多出现两次，不需要考虑数组中超出新长度后面的元素)
 * 
 * 思路：类似partition，将数组分为重复次数不超过两次的部分和无需考虑的部分
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int k = 0; // 重复次数不超过两次的区间(-无穷 ,k]
        for (int i = 1; i < nums.length; i++) {
            // 当前元素第一次出现或之前只出现一次
            // [1, 1, 1, 2, 2, 3]
            //nums[k - 1]       nums[i]
            // [1,           1]   1  , nums[i] == nums[k - 1]
            // [1,           1]   2  , nums[i] > nums[k - 1]
            if (k - 1 < 0 || nums[i] > nums[k - 1]) {
                nums[++k] = nums[i];
            }
        }

        return k + 1;
    }
}

