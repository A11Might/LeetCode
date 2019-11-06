import java.util.Arrays;

/**
 * @author qhhu
 * @date 2019/11/6 - 9:29
 *
 * [343] 整数拆分
 *
 * 题目: 将给定的一个正整数n拆分为至少两个正整数的和, 使得这些正整数的乘积最大化, 返回获得的最大乘积
 *
 * 难度: medium
 *
 * 思路: 动态规划, f(n) = max(i * (n - i), 1 * f(n - i)), 1 <= i < n
 *                             n - i不继续拆分  n - i继续拆分
 */
class Solution {
    // 递归
    public int integerBreak1(int n) {
        return dfs(n);
    }

    // 将n进行分割(至少分割两部分), 可以获得的最大乘积
    private int dfs(int n) {
        if (n == 1) {
            return 1;
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            // i + (n - i)
            //                                n - i不继续拆分  n - i继续拆分
            ans = Math.max(ans, Math.max(i * (n - i) ,i * dfs(n - i)));
        }

        return ans;
    }

    // 记忆化搜索: 自顶而下
    public int integerBreak2(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    // 将n进行分割(至少分割两部分), 可以获得的最大乘积
    private int dfs(int n, int[] memo) {
        if (n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            // i + (n - i)
            //                                n - i不继续拆分  n - i继续拆分
            ans = Math.max(ans, Math.max(i * (n - i) ,i * dfs(n - i)));
        }
        memo[n] = ans;

        return ans;
    }

    // 动态规划: 自底而上
    // 自顶而下的思考问题的结构, 再反过来推自底而上的双重循环如何解决问题
    public int integerBreak(int n) {
        // dp[i]表示将i进行分割(至少分割两部分), 可以获得的最大乘积
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 计算dp[i]
            for (int j = 1; j < i; j++) {
                // j + (i - j)
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }
}
