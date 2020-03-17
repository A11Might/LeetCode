import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 * 
 * 题目：找到无序整数数组的最长上升子序列的长度
 *
 * 难度：medium
 * 
 * 思路：动态规划。
 */
class Solution {
    /**
     * 划分型动态规划：有一种将当前元素接在 dp[i] 后面变成一段这种状态转移的思想。
     * 状态表示：dp[i] 表示以 i 元素结尾的最长上升子序列的长度。
     * 状态转移方程：dp[i] = max{1, dp[j] + 1 | Sj < Si && j < i}
     * 初始状态：每个位置的至少有由自己的构成的上升子序列，长度为 1。
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) return 0;
        int len = nums.length;
        // 最长上升子序列的长度
        int ans = 0;
        int[] dp = new int[len];

        // 决策边界
        // 每个位置的至少有由自己的构成的上升子序列，长度为 1。
        Arrays.fill(dp, 1);

        // 状态转移
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 当前元素比 nums[j]大，以当前元素为结尾的最长上升子序列的长度可以为 dp[j] + 1。
                // dp[i] 选取最长上升子序列的长度
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = dp[i] > ans ? dp[i] : ans;
        }

        return ans;
    }

    /**
     * 划分型动态规划：有一种将当前元素接在 dp[i] 后面变成一段这种状态转移的思想。
     * 状态表示：dp[i] 表示所有长度为 i 的递增子序列中，最小的那个序列尾数。
     * 状态转移方程：由定义知 dp 数组必然是一个递增数组，可以用 maxL 来表示最长递增子序列的长度。
     *             对数组进行迭代，依次判断每个数 num 将其插入 dp 数组相应的位置：
     *             1. num > dp[maxL]，表示 num 比所有已知递增序列的尾数都大，将 num 添加入 dp。
     *                数组尾部, 并将最长递增序列长度 maxL 加 1。
     *             2. dp[i-1] < num <= dp[i], 只更新相应的 dp[i]。
     * 初始状态：长度为 1 的序列初始最小尾数为第一个元素。
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(n)
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) return 0;
        int len = nums.length;
        // 最长递增子序列的长度
        int maxL = 0;
        int[] dp = new int[len + 1];
        for (int num : nums) {
            // 二分查找，找到第一个大于等于当前元素 nums[i] 的元素，即将 dp 数组分为 < 和 >= 部分。
            // 当前 num 大于 dp[lo - 1] 所以 num 是长度为 lo 的序列尾数，
            // 并且 num 小于等于 dp[i] 所以 num 可以更新长度为 lo 的递增子序列中，最小的那个序列尾数。
            int lo = 1, hi = maxL;
            while (lo <= hi) {
                int mid = lo + ((hi - lo) >> 1);
                if (dp[mid] < num) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            dp[lo] = num;
            if (lo > maxL) maxL = lo;
        }

        return maxL;
    }
}

