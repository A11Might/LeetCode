/**
 * @author qhhu
 * @date 2019/12/11 - 10:02
 *
 * [338] 比特位计数
 *
 * 题目: 给定一个非负整数 num, 对于 0 <= i <= num 范围的每个数字 i, 计算器二进制数中的 1 的个数并将它们作为数组返回.
 *
 * 难度: medium
 *
 * 思路: i & (i - 1)可以去掉 i 最右边的一个 1, 因此 i & (i - 1）是比 i 小的, 而且 i & (i - 1) 的 1 的个数已经在前面算过了,
 *      所以 i 的 1 的个数就是 i & (i - 1) 的 1 的个数加上 1, 即 dp[i] = dp[i & (i - 1)] + 1.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }

        return dp;
    }
}
