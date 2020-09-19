/**
 * @author qhhu
 * @date 2019/11/6 - 9:29
 *
 * [343] 整数拆分
 *
 * 题目：将给定的一个正整数 n 拆分为至少两个正整数的和，使得这些正整数的乘积最大化，返回获得的最大乘积。
 *
 * 难度：medium
 *
 * 思路：1. 划分型动态规划：将整数划分为 k 段。
 *          状态表示：dp[i] 表示将整数 i 划分 k 段后的最大乘积。
 *          状态转移方程：题意说至少要划分成两段，所以有两种情况：`划分成两段`和`划分成若干段（大于两段）`。
 *                      dp[n] = max(max(1 * (n - 1), 1 + dp[n - 1]), max(2 * (n - 2), 2 + dp[n - 2]), ...)
 *          初始状态：无
 *      2. 尽可能分成 3 和 2，最多只能有 2 个 2
 *         证明：a、ai >= 5 时，ai = 3 + (ai - 3)，而 3 * (ai - 3) = 3ai - 9 是大于 ai 的（ai > 4.5），所以需要拆分成 3 和另一个数
 *               b、不包含 1，因为 1 * x * y = x * y < (x + 1) * y
 *               c、不包含 4，因为 ai = 4 = 2 + 2
 *               d、不包含多于 2 个 的2，因为 2 + 2 + 2 = 6 = 3 + 3，但 2 * 2 * 2 = 8 而 3 * 3 = 9
 */
class Solution {
    /**
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];

        // 状态转移
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int integerBreak(int n) {
        if (n <= 3) return 1 * (n - 1);
        int product = 1;
        while (n >= 5) {
            n -= 3;
            product *= 3;
        }
        // 最后剩余的只会是 2、3、4，直接乘积
        return product * n;
    }
}