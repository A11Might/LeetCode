/**
 * @author qhhu
 * @date 2019/12/18 - 9:40
 *
 * [413] 等差数列划分
 *
 * 题目: 返回给定数组中所有为等差数组的子数组个数
 *      (子数组长度 >= 3)
 *
 * 难度: medium
 *
 * 思路: 动态规划
 *        dp[i]表示在区间(k, i)中, 而不在区间(k, j)中等差数列的个数, 其中 j < i
 *       假设A数组的子数组[k, i - 1]为等差数列, dp[i - 1] = x, 当A[i] - A[i - 1] == A[i - 1] - A[i - 2]时,
 *       子数组[k, i]也为等差数列, 新增满足条件的子数组为[k, i], [k + 1, i], ..., [i - 2, i],
 *       除[i - 2, i], [k, i]可以对应[k, i - 1], [k + 1, i]可以对应[k + 1, i - 1], ..., [i - 3, i]可以对应[i - 3, i - 1]
 *       所以dp[i] = dp[i - 1] + 1
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        // dp[i]表示在区间(k, i)中, 而不在区间(k, j)中等差数列的个数, 其中 j < i
        // 即当前新增了多少为等差数组的子数组
        int[] dp = new int[len];
        int ans = 0;
        for (int i = 2; i < len; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
        }

        return ans;
    }
}
