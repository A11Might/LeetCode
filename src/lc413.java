/**
 * @author qhhu
 * @date 2019/12/18 - 9:40
 *
 * [413] 等差数列划分
 *
 * 题目：返回给定数组中所有为等差数组的子数组个数。要求子数组长度 >= 3。
 *
 * 难度：medium
 *
 * 思路：序列型动态规划：给定的是一个数组。
 *      状态表示：dp[i] 表示以 A[i] 为结尾的等差数列的个数。
 *      状态转移方程：假设 A 数组的子数组 [j, i - 1] 为等差数列，dp[i - 1] = x，当 A[i] - A[i - 1] == A[i - 1] - A[i - 2] 时，
 *      子数组 [j, i]也为等差数列，则以 A[i] 为结尾的等差数列的个数为：x + 1。包括之前以 A[i - 1] 结尾的等差数列的个数，即以 A[i - 1]
 *      结尾的等差数列后加上 A[i] 这个元素构成的等差数列 [j, i], [j + 1, i], ..., [i - 3, i]，再加上 [i - 2, i] 这个新增等差数列。
 *      综上，在 A[i] - A[i - 1] == A[i - 1] - A[i - 2] 时，dp[i] = dp[i - 1] + 1。
 *      初始状态：当 i < 2 时，dp[i] = 0。
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        int[] dp = new int[len];
        int ret = 0;

        // 决策边界，当 i < 2 时，dp[i] = 0。

        // 状态转移
        for (int i = 2; i < len; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                ret += dp[i];
            }
        }

        return ret;
    }
}
