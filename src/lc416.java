/*
 * @lc app=leetcode.cn id=416 lang=java
 *
 * [416] 分割等和子集
 *
 * 题目：判断一个包含正整数的非空数组，能否分割成两个元素和相等的子集
 *
 * 难度: medium
 *
 * 思路：典型的背包问题, 在n个物品中选出一定物品, 填满sum / 2 的背包
 *      状态转移方程, f(i, c) = f(i - 1, c) || f(i - 1, c - w(i))
 *      状态f(n, c)考虑将n个物品填满容量为c的背包
 *
 *       0 1 2 3 4 5 c
 *      0
 *      1
 *      i
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        return canPartitionCore(nums, sum / 2, 0, 0);
    }

    private boolean canPartitionCore(int[] nums, int target, int curSum, int index) {
        if (index == nums.length) return curSum == target;
        if (curSum == target) return true;
        if (curSum > target) return false;
        return canPartitionCore(nums, target, curSum, index + 1) ||
                canPartitionCore(nums, target, curSum + nums[index], index + 1);
    }

    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        // dp[i][c]表示使用索引[0...i]的这些元素, 是否可以完全填充一个容量为c的背包
        boolean[][] dp = new boolean[nums.length][target + 1];
        for (int j = 0; j <= target; j++) {
            // 与一般背包问题不同的是, 这题的物品没有价值但需要填满背包
            dp[0][j] = j == nums[0] ? true : false;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                // 不加入第i个物品
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i]) {
                    // 加入第i个物品
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][target];
    }

    // 优化空间复杂度: 第i行的元素只依赖于第i - 1行元素. 理论上, 只需要保持两行元素即可(解法略过)
    // 优化空间复杂度: 使用一行数组, 从有往左更新dp值
    public boolean canPartition2(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        for (int j = 0; j <= target; j++) {
            dp[j] = j == nums[0] ? true : false;
        }
        for (int i = 1; i < nums.length; i++) {
            // 再之前的元素不用动(因为无法放入w[i]了, 其值即为下一行的值)
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }
}