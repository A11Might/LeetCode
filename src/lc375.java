/**
 * @author qhhu
 * @date 2019/12/14 - 10:11
 *
 * [375] 猜数字大小 II
 *
 * 题目: 猜从1到n之间的一个数字, 若你猜x猜错的会知道猜大猜小了, 但需要支付x元, 返回至少要有多少现金才能确保你能赢得这个游戏
 *
 * 难度: medium
 *
 * 思路: 动态规划, f(i, j)表示从[i, j]中猜出正确数字所需要的的最少花费金额
 *       状态转移方程: f(i, j) = min(max(f(i, x - 1), f(x + 1, j) + x), x ~ [i, j]
 *       为了确保能赢, 应该假设要猜数字在较大方向, 往大了选择(若往小了猜赢了, 当它给出的数字时大的方向就无法赢得游戏)
 */
class Solution {
    public int getMoneyAmount(int n) {
        // 初始化为(n+2)*(n+2)数组的原因: 处理边界情况更加容易(每次都想不到硬写, 写得又臭又长)
        int[][] dp = new int[n + 2][n + 2]; // dp[i][j]表示从[i, j]中猜出正确数字所需要的的最少花费金额
        //      0  1  2  3  4
        //
        // 0    0  0  0  0  0
        // 1    0  0  f  f  0
        // 2    0  0  0  f  0
        // 3    0  0  0  0  0
        // 4    0  0  0  0  0
        // dp[i][x - 1]在dp[i][j]的左边, dp[x + 1][j]在dp[i][j]的下边所以从下往上更新dp表
        // i <= j, 所以只用更新半个矩阵
        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = 0; // 只有一个数, 肯定一次猜中
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    // 分别猜x ~ [i, j]
                    for (int x = i; x <= j; x++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][x - 1], dp[x + 1][j]) + x);
                    }
                }
            }
        }

        return dp[1][n];
    }
}
