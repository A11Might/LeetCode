/*
 * @lc app=leetcode.cn id=27 lang=java
 *
 * [27] 移除元素
 * 
 * 题目：移除给定数组中值为val的元素(不需要考虑数组中超出新长度后面的元素)
 * 
 * 思路：类似partition，将数组分为不含val部分和只含val部分
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int k = -1; // 不含val的区间(-无穷, k]
        for (int i = 0; i < nums.length; i++) {
            // 当前值不为val时，放入不含val的区间
            if (nums[i] != val) {
                nums[++k] = nums[i];
            }
            // 当前值为val时，跳过
        }

        return k + 1;
    }
}

