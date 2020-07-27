/*
 * @lc app=leetcode.cn id=152 lang=java
 *
 * [152] 乘积最大子序列
 *
 * 题目：给定一个整数数组nums, 返回序列中乘积最大的连续子序列（该序列只要包含一个数）
 *
 * 难度：medium
 * 
 * 思路：动态规划，与 lc53 最大子序和不同的是：最大子序和只需关注 dp[i - 1]（以 nums[i - 1] 结尾的子数组和最大值）的正负，即可得到当前位置的 dp 值
 *              而本题需要同时关注 dp[i - 1] 的最大值和最小值, 用于 [-3, 1, -2] 这种情况（负负得正）
 *                                                                   ^
 *                                                                   |
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // maxProduct记录以 nums[i-1] 结尾的子数组乘积最大值, minProduct记录以 nums[i-1] 结尾的子数组乘积最小值
        // 因为计算乘积所有初始值为1
        int minProduct = 1, maxProduct = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = minProduct * nums[i];
            int tmp2 = maxProduct * nums[i];

            // 实时更新当前最大值最小值, 从以下三个值中挑选
            // 比较以当前元素、以 nums[i-1] 结尾的子数组乘积最大值加上当前元素、以 nums[i-1] 结尾的子数组乘积最小值加上当前元素构成的这三个子数组的值
            minProduct = Math.min(nums[i], Math.min(tmp, tmp2));
            maxProduct = Math.max(nums[i], Math.max(tmp, tmp2));

            ans = Math.max(ans, maxProduct);
        }
        return ans;
    }
}

