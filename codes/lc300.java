import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 * 
 * 题目：找到无序整数数组的最长上升子序列的长度
 * 
 * 思路：1、动态规划，dp[i] = max{dp[i], 1 + dp[j] if j < i and nums[i] > nums[j]}
 *      2、贪心算法，遍历原数组试图找到最长上升子序列，让找到的序列里面的元素的值尽量小
 */
class Solution {
    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // dp[i]为以nums[i]结尾的最长上升子序列的长度
        Arrays.fill(dp, 1); // 每个位置的至少有由自己的构成的上升子序列，长度为1
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                // 当前元素比nums[j]大，以当前元素为结尾的最长上升子序列的长度可以为dp[j] + 1
                // dp[i]选取最长上升子序列的长度
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); 
                }
            }
        }
        int res = 0;
        for (int len : dp) {
            res = Math.max(res, len);
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] longestLTS = new int[nums.length];
        longestLTS[0] = nums[0];
        int maxLen = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > longestLTS[maxLen]) {
                longestLTS[++maxLen] = nums[i];
            // 由于上升子序列需要严格单调，不能存在相等的元素
            // 当当前元素小于等于上升子序列最后一个元素时，为让整个序列里面的元素尽量小
            // 将序列中第一个大于或等于当前元素替换为当前元素
            } else {
                // 二分查找，将数组分为< 和 >= 两部分
                int lo = 0, hi = maxLen;
                while (lo < hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (longestLTS[mid] < nums[i]) { // 在longestLTS中找到第一个不小于当前元素的元素，将其替换为当前元素
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }
                longestLTS[lo] = nums[i];
            }
        }
        return maxLen + 1;
    }
}

