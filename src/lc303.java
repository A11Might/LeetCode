/**
 * @author qhhu
 * @date 2019/12/7 - 14:46
 *
 * [303] 区域和检索 - 数组不可变
 *
 * 题目: 给定一个整数数组nums, 返回数组[i, j]范围内元素的总和
 *
 * 难度: easy
 *
 * 思路: 动态规划, sum[i]是数组前i的和
 *                状态转移方程: sumrange（i，j）=sum[j+1]−sum[i]
 */
class NumArray {
    private int[] dp;

    public NumArray(int[] nums) {
        int len = nums.length;
        // dp[i]是数组前i的和
        dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        // j为第j + 1项, dp[i]为前i - 1项和
        return dp[j + 1] - dp[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
