/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 * 
 * 题目：改变一个非负整数数组中元素的符号, 返回数组和为目标数的方法数
 *
 * 难度: medium
 * 
 * 思路：1、回溯算法, 考虑每个元素前的符号
 *      2、动态规划0-1背包问题, dp[i][j] 表示用数组中的前 i 个元素, 组成和为 j 的方案数
 *         状态转移方程: dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
 */
class Solution {
    /**
     * 时间复杂度: O(2 ^ n)
     * 空间复杂度: O(n), 递归栈的深度为n
     */
    public int findTargetSumWays(int[] nums, int S) {
        int[] times = {0};
        dfs(nums, 0, S, times);
        return times[0];
    }

    private void dfs(int[] nums, int index,int restS, int[] times) {
        if (index == nums.length) {
            times[0] += restS == 0 ? 1 : 0;
            return;
        }
        dfs(nums, index + 1, restS - nums[index], times); // 当前元素符号为'+'
        dfs(nums, index + 1, restS + nums[index], times); // 当前元素符号为'-'
    }

    /**
     * 时间复杂度: O(n * sum)
     * 空间复杂度: O(n * sum)
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int len = nums.length;
        // 由于数组中所有数的和不超过 1000, 那么 j 的最小值可以达到 -1000
        // 为了使用数组索引表示, 需要给 dp[i][j] 的第二维预先增加 1000
        int[][] dp = new int[len][2001];
        dp[0][1000 - nums[0]] += 1;
        dp[0][1000 + nums[0]] += 1;
        // 就想着写完base case后填表就行, 不要多想(vip)
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < 2001; j++) {
                if (j - nums[i] >= 0 && j - nums[i] < 2001) {
                    dp[i][j] += dp[i - 1][j - nums[i]]; // 当前元素符号为 '-'
                }
                if (j + nums[i] >= 0 && j + nums[i] < 2001) {
                    dp[i][j] += dp[i - 1][j + nums[i]]; // 当前元素符号为 '+'
                }
            }
        }

        if(1000 + S < 0 || 1000 + S >= 2001) {
            return 0;
        }
        return dp[len - 1][1000 + S];
    }

    // 方法二中动态规划的状态转移方程中，dp[i][...] 只和 dp[i - 1][...]有关
    /**
     * 时间复杂度: O(n * sum)
     * 空间复杂度: O(sum)
     */
    public int findTargetSumWays3(int[] nums, int S) {
        int len = nums.length;
        int[] dp = new int[2001];
        dp[1000 - nums[0]] += 1;
        dp[1000 + nums[0]] += 1;
        for (int i = 1; i < len; i++) {
            int[] temp = new int[2001];
            for (int j = 0; j < 2001; j++) {
                if (j - nums[i] >= 0 && j - nums[i] < 2001) {
                    temp[j] += dp[j - nums[i]]; // 当前元素符号为 '-'
                }
                if (j + nums[i] >= 0 && j + nums[i] < 2001) {
                    temp[j] += dp[j + nums[i]]; // 当前元素符号为 '+'
                }
            }
            dp = temp;
        }

        if(1000 + S < 0 || 1000 + S >= 2001) {
            return 0;
        }
        return dp[1000 + S];
    }
}
