/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 * 
 * 题目：改变一个非负整数数组中元素的符号，返回数组和为目标数的方法数
 * 
 * 思路：1、回溯算法，考虑每个元素符号变与不变
 *      2、动态规划？？？？？？？？
 */
class Solution {
    private int times = 0;

    public int findTargetSumWays1(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return S == 0 ? 1 : 0;
        }
        process(nums, S, 0);
        return times;
    }

    private void process(int[] nums, int target, int index) {
        if (index == nums.length) {
            times += target == 0 ? 1 : 0;
        } else {
            process(nums, target + nums[index], index + 1); // 当前元素符号不变
            process(nums, target - nums[index], index + 1); // 当前元素符号变化
        }
    }

    /**
     * sum(+)符号应为+的元素和，sum(-)符号应为-的元素和(都是正数) 则sum(+) - sum(-) = target => sum(all) +
     * sum(+) - sum(-) = target + sum(all) => 2 * sum(+) = target + sum(all) =>
     * sum(+) = (target + sum(all)) / 2 因此题目转化为01背包，也就是能组合成容量为sum(+)的方式有多少种
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) { // nums为非负整数，sum(+)不可能有小数
            return 0;
        }
        int w = (sum + S) / 2;
        int[] dp = new int[w + 1];
        dp[0] = 1; // 和是0的解 一定有一个 就是一个都不选
        for (int num : nums) {
            // 这个循环是 每次拿出一个数， 用它还是不用，算出和是 dp[i]的解的数量
            // 因为要算和是i 所以 当num > i 时 是不用算的
            for (int i = w; i >= num; i--) {
                // 这行 依然是 01背包算法
                // dp[i] 是不用 num 和是 i 的解的个数
                // dp[i-num] 是用 num 和是 i 的解的个数
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[w];
    }
}
