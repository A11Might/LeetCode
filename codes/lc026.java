/*
 * @lc app=leetcode.cn id=26 lang=java
 *
 * [26] 删除排序数组中的重复项
 * 
 * 题目：对给定排序数组去重(不需要考虑数组中超出新长度后面的元素)
 * 
 * 思路：类partition，将数组分为去重后数组部分和无须考虑部分
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int k = 0; // 存放去重后数组的区间(-无穷, k]
        for (int i = 1; i< nums.length; i++) {
            // 当前元素第一次出现，则加入去重后数组的区间
            if (nums[i] != nums[k]) {
                nums[++k] = nums[i]; // 直接覆盖前面元素，不考虑超出新长度的元素
            }
            // 当前元素重复出现，则跳过
        }

        return k + 1;
    }
}

