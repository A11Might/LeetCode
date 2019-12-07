import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 * 
 * 题目：找到无序整数数组的最长上升子序列的长度
 *
 * 难度:  medium
 * 
 * 思路：1、动态规划, dp[i] = max{dp[i], 1 + dp[j] if j < i and nums[i] > nums[j]}
 *      2、动态规划, O(n * logn)的方法
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len]; // dp[i]为以nums[i]结尾的最长上升子序列的长度
        int ans = 0; // 最长上升子序列的长度
        for (int i = 0; i < len; i++) {
            dp[i] = 1; // 每个位置的至少有由自己的构成的上升子序列，长度为1
            for (int j = 0; j < i; j++) {
                // 当前元素比nums[j]大，以当前元素为结尾的最长上升子序列的长度可以为dp[j] + 1
                // dp[i]选取最长上升子序列的长度
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = dp[i] > ans ? dp[i] : ans;
        }

        return ans;
    }

    /**
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(n)
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // dp[i]表示所有长度为i的递增子序列中, 最小的那个序列的最后一个元素
        int dp[] = new int[len + 1];
        int maxL = 1; // 最长递增子序列的长度
        dp[1] = nums[0];
        for (int i = 1; i < len; i++) {
            // num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp数组尾部, 并将最长递增序列长度maxL加1
            if (nums[i] > dp[maxL]) {
                dp[++maxL] = nums[i];
            // num <= dp[i], 只更新相应的dp
            } else {
                // 二分查找, 找到第一个大于等于当前元素nums[i]的元素
                // 即将dp数组分为< 和 >= 部分
                int lo = 0, hi = maxL + 1; // [lo, hi)区间内是为确定< 或者 >= 的部分
                while (lo < hi) {
                    int mid = lo + ((hi - lo) >> 1);
                    if (dp[mid] < nums[i]) {
                        lo = mid + 1;
                    } else {
                        lo = mid;
                    }
                }
                dp[lo] = nums[i];
            }
        }

        return maxL;
    }
}

