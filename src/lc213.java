/**
 * @author qhhu
 * @date 2019/11/14 - 9:23
 *
 * [213] 打家劫舍 II
 *
 * 题目: 给定一个代表每个房屋存放金额的非负整数数组, 计算在不触动警报装置的情况下, 能够窃取的最高金额
 *      (所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的,
 *      如果相邻的两个房屋在同一个晚上被偷, 系统会报警)
 *
 * 难度: medium
 *
 * 思路: 第一次求nums[0]...nums[n-2],第二次求nums[1]...nums[n-1],两次中的最大值即为结果
 *      第一次求解中, 若偷盗nums[0], 则nums[n - 1]不可再偷, 算出的金额需要对比不偷盗nums[0]的情况, 即为第二次求解
 *      第一次求解中, 若不偷盗nums[0], 则nums[n- 1]可偷, 所以结果为第二次求解(第一次相当于求解[1, n - 2]必定小于等于第二次求解)
 */
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }

    private int rob(int[] nums, int start, int end) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[start] = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (i - 2 < 0) {
                dp[i] = Math.max(nums[i], dp[i - 1]);
            } else {
                dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            }
        }

        return dp[end];
    }
}
