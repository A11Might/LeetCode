/**
 * @author qhhu
 * @date 2019/11/27 - 9:31
 *
 * [376] 摆动序列
 *
 * 题目: 如果连续数字之间的差值严格地在正数和负数之间交替, 则该序列成为摆动序列, 少于两个元素的序列也是摆动序列
 *      给定一个整数序列, 返回作为摆动序列的最长子序列的长度
 *
 * 难度: medium
 *
 * 思路: 1. 动态规划, 状态转移方程: f(i, 0) = max(f(j, 1) + 1) if nums[i] > nums[j]
 *                        and f(i, 1) = max(f(j, 0) + 1) if nums[i] < nums[j]
 *       2. 动态规划,
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        // dp[i][0]表示以nums[i]结尾最后一次差值为正值的作为摆动序列的最长子序列的长度
        // dp[i][1]表示以nums[i]结尾最后一次差值为负值的作为摆动序列的最长子序列的长度
        int[][] dp = new int[len][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] - nums[j] > 0) {
                    dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
                } else if (nums[i] - nums[j] < 0) {
                    dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
                } else { // 实例[0, 0]
                    dp[i][0] = Math.max(dp[i][0], dp[j][0]);
                    dp[i][1] = Math.max(dp[i][1], dp[j][1]);
                }
            }
        }

        // 不管大于小于等于都会更新当前dp值, 即当前可以获得的作为摆动序列的最长子序列的长度, 所以最后即为最大值
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int wiggleMaxLength2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        // dp[i][0]表示以nums[i]结尾最后一次差值为正值的作为摆动序列的最长子序列的长度
        // dp[i][1]表示以nums[i]结尾最后一次差值为负值的作为摆动序列的最长子序列的长度
        int[][] dp = new int[len][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            // 这里在摆动上升, 若前一个数字处于下降的位置, up[i] = down[i - 1] + 1
            // 若前一个数字处于上升的位置, down[i]与down[i − 1]保持相同
            if (nums[i] > nums[i - 1]) {
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = dp[i - 1][1];
            } else if (nums[i] < nums[i - 1]) {
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
            }
        }

        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    // 优化方法二的
    public int wiggleMaxLength3(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return Math.max(down, up);
}
