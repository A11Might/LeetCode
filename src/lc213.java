/**
 * @author qhhu
 * @date 2019/11/14 - 9:23
 *
 * [213] 打家劫舍 II
 *
 * 题目：给定一个代表每个房屋存放金额的非负整数数组，计算在不触动警报装置的情况下，能够窃取的最高金额。
 *      (所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的，如果相邻的两个房屋在同一个晚上被偷，系统会报警。)
 *
 * 难度: medium
 *
 * 思路：序列型动态规划。
 *      环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，因此可以把此环状排列房间问题约化为两个单排排列房间子问题：
 *      a. 在不偷窃第一个房子的情况下（即 nums[1:]），最大金额是 p_1;
 *      b. 在不偷窃最后一个房子的情况下（即 nums[:n-1]），最大金额是 p_2
 *      综合偷窃最大金额：为以上两种情况的较大值，即 max(p1, p2)。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 序列型动态规划：一排房子有先后顺序，类似序列。
     * 状态表示：dp[i] 表示偷了前 i 个房子的最大收益。由于相邻的房子不能同时偷，但是我们的状态表示没有办法直到最后一个房子偷没偷。
     *         所以状态表示增加一维 dp[i][0/1] 表示偷了前 i 个房子，最后一个是否偷了的最大非法获利。
     * 状态转移方程：很容易得到 dp[i][0] = max(dp[i - 1][1], dp[i - 1][0])
     *                      dp[i][1] = dp[i - 1][0] + nums[i]
     *             即考虑第 i 个房子的时候：偷它，前一个就不能偷；不偷它，前一个可偷可不偷。
     *             最终的答案为 max(dp[n][0], dp[n][1])。
     * 初始状态：没有房子时收益为 0。
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        return Math.max(robCore(nums, 0, len - 2), robCore(nums, 1, len - 1));
    }

    private int robCore1(int[] nums, int start, int end) {
        int len = end - start + 1;
        int[][] dp = new int[len + 1][2];

        // 决策边界为多出的一维

        // 状态转移
        for (int i = 1; i <= len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[start++];
        }

        return Math.max(dp[len][0], dp[len][1]);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 序列型动态规划：一排房子有先后顺序，类似序列。
     * 状态表示：dp[i] 表示偷了前 i 个房子的最大收益。由于相邻的房子不能同时偷，但是我们的状态表示没有办法直到最后一个房子偷没偷。
     *         所以改变状态表示意义 dp[i] 表示偷了前 i 个房子，最后一个一定偷的最大非法获利。
     * 状态转移方程：很容易得到 dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
     *             即如果抢劫了第 i -1 个住户，那么就不能再抢劫第 i 个住户。
     * 初始状态：没有房子时收益为 0。
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        return Math.max(robCore(nums, 0, len - 2), robCore(nums, 1, len - 1));
    }

    private int robCore(int[] nums, int start, int end) {
        int pre2 = 0, pre1 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }

        return pre1;
    }
}
