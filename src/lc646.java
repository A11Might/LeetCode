import java.util.Arrays;

/**
 * @author qhhu
 * @date 2020/3/9 - 8:54
 *
 * [646] 最长数对链
 *
 * 题目：给定 n 个数对。每对数对中第一个数字总是小于第二个数字。当前仅当 b < c 时，数对 (c, d) 才可以跟在 (a, b) 后面，构成一个数对链。
 *      返回可以形成的最长数对链的长度。
 *
 * 难度： medium
 *
 * 思路：1. 动态规划，同 [300] 最长上升子序列。
 *         划分型动态规划：有一种将当前数对接在 dp[i] 后面变成一段这种状态转移的思想。
 *         状态表示：dp[i] 表示以第 i 个数对结尾的最长数对链的长度。
 *         状态转移方程：dp[i] = max{1, dp[j] + 1 | pairs[j][1] < pairs[i][0] && j < i}
 *         初始状态：每个位置的至少有由自己的构成的数对链，长度为 1。
 *      2. 贪心策略，使用贪心思想扩展数对链，在所有可作为下一个数对的集合中选择第二个数最小的数对添加到数对链。
 */
class Solution {
    /**
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int findLongestChain1(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[0] - o2[0]);
        int len = pairs.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ret = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }

        return ret;
    }

    /**
     * 时间复杂度：O(n * logn)
     * 空间复杂度：O(n)
     */
    public int findLongestChain2(int[][] pairs) {
        // 按照数对第二个数的升序序列遍历所有数对，如果当前数对可以加入链，则加入。
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);
        int cur = Integer.MIN_VALUE;
        int cnt = 0;
        for (int[] pair : pairs) {
            if (pair[0] > cur) {
                cur = pair[1];
                cnt++;
            }
        }

        return cnt;
    }
}