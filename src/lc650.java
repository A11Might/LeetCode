/**
 * @author qhhu
 * @date 2020/3/12 - 9:01
 *
 * [650] 只有两个键的键盘
 *
 * 题目：最开始只有一个字符 A，问需要多少次操作能够得到 n 个字符 A，每次操作可以复制当前所有的字符，或者粘贴。
 *
 * 难度：medium
 *
 * 思路：1.
 *      2. 动态规划，
 *         状态表示：dp[i] 表示通过复制粘贴操作，得到 i 个字符最少的操作数。
 *         状态转移方程：如果一个数是素数，那么最少操作就是一开始复制一个，最后一个个粘贴；
 *                    如果一个数不是素数，那么最少操作就可以按它的因数分解一下，简化操作。
 *                    举个例子，比如 12，可以分解为 以下几种情况：
 *                            12 = 2*6, 需要操作CPCPPPPP总共8步
 *                            12 = 3*4, 需要操作CPPCPPP总共7步
 *                            12 = 4*3, 需要操作CPPPCPP总共7步
 *                            12 = 6*2, 需要操作CPPPPPCP总共8步
 *         所以因子相同的情况下，交换因子相乘的顺序，需要的步骤是一样的。所以我们可以简化一下分解的步骤，只需要找到小于 sqrt(n) 的因子即可。
 *         假设找到的因子是 j，那么需要的最小步骤就是 dp[j] + dp[i / j]，其中，dp[j]表示需要多少步生成这个因子，dp[i/j]表示需要多少步
 *         基于这个因子得到 i。
 *         初始状态：dp[0] = 1。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int minSteps1(int n) {
        if (n == 1) return 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return i + minSteps1(n / i);
        }
        return n;
    }

    /**
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int minSteps2(int n) {
        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);

        // 决策边界

        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }

        return dp[n];
    }
}