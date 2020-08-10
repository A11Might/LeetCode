/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 * 
 * 题目：在给定的正整数数组中，找到和 >= 正整数 s 的最短连续子数组
 *      （不存在则返回 0）
 * 
 * 难度：medium
 * 
 * 思路：双指针，对于每个位置 i，找到尽可能小的 j，满足 [j, i] 的和大于等于 s，然后更新答案。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int ans = n + 1;
        for (int i = 0, j = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            while (sum - nums[j] >= s) sum -= nums[j++];
            if (sum >= s) ans = Math.min(ans, i - j + 1);
        }
        if (ans == n + 1) return 0;
        return ans;
    }
}