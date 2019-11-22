/**
 * @author qhhu
 * @date 2019/11/22 - 9:42
 *
 * [377] 组合总和 IV
 *
 * 题目:给定一个有正整数组成且不存在重复数字的数组, 返回和为给定目标正整数的组合的个数
 *     (顺序不同的序列被视为不同的组合)
 *
 * 难度: medium
 *
 * 思路: 动态规划0-1背包问题
 *      状态转移方程: f(n) = sum(1 * f(n - nums[i])) i in [0, nums.length)
 */
class Solution {
    /**
     * 时间复杂度: O(target * nums.length)
     * 空间复杂度: O(target)
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        // dp代表和为i的组合的个数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                // 累计所有组合
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }
}
