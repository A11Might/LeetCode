/**
 * @author qhhu
 * @date 2019/11/25 - 9:21
 *
 * [474] 一和零
 *
 * 题目: 给定m个0, n个1和仅包含0和1组成的字符串的数组, 返回给定m个0和n个1能拼出存在于数组中的字符串的最大数量
 *
 * 难度: medium
 *
 * 思路: 动态规划0-1背包问题, 这道题的背包有0和1两种容量, 每个物品(字符串)需要分别占用0和1的若干容量
 *      dp[i][j]表示使用i个0和j个1能拼出的字符串的数量
 *      状态转移方程: dp(i, j) = max(1 + dp(i - cost_zero[k], j - cost_one[k])) if i >= cost_zero[k] and j >= cost_one[k]
 *      其中 k 表示第 k 个字符串，cost_zero[k] 和 cost_one[k] 表示该字符串中 0 和 1 的个数
 */
class Solution {
    /**
     * 时间复杂度: O(strs.length * m * n)
     * 空间复杂度: O(m * n)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = countZerosOnes(str);
            // 由于每个字符串只能使用一次（即有限背包），因此在更新 dp(i, j) 时，i 和 j 都需要从大到小进行枚举(vip)
            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count[0]][j - count[1]]);
                }
            }
        }

        return dp[m][n];
    }

    private int[] countZerosOnes(String str) {
        int[] ans = {0, 0};
        for (char chr : str.toCharArray()) {
            ans[chr - '0']++;
        }

        return ans;
    }
}
