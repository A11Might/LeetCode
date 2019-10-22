/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 * 
 * 题目：在给定的正整数数组中，找到和>=正整数s的最短连续子数组
 *      (不存在则返回0)
 * 
 * 思路：滑动窗口
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int l = 0, r = -1; // 滑动窗口[l, r]，初始r为-1，表示窗口中无元素
        int sum = 0; // 当前窗口中元素的和
        int res = n + 1; // 当前和>=正整数s的最短连续子数组长度
        while (l < n) {
            // 窗口中元素和小于s，则移动右边界扩大窗口，增加窗口和
            if (r + 1 < n && sum < s) {
                sum += nums[++r];
            // 窗口中元素和大于等于s，则移动左边界缩小窗口，寻找下一个和>=s的窗口
            } else {
                sum -= nums[l++];
            }
            
            // 实时更新和>=正整数s的最短连续子数组长度
            if (sum >= s) {
                res = Math.min(res, r - l + 1);
            }
        }
        
        // 不存在符合条件的连续子数组，返回 0
        if (res == n + 1) {
            return 0;
        }
        return res;
    }
}

