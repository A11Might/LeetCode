/**
 * @author qhhu
 * @date 2019/12/11 - 10:02
 *
 * [338] 比特位计数
 *
 * 题目: 给定一个非负整数num, 对于0 <= i <= num范围的每个数字i, 计算器二进制数中的1的个数并将它们作为数组返回
 *
 * 难度: medium
 *
 * 思路: i & (i - 1)可以去掉i最右边的一个1, 因此 i & (i - 1）是比 i 小的, 而且i & (i - 1)的1的个数已经在前面算过了
 *       所以i的1的个数就是 i & (i - 1)的1的个数加上1
 */
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }
}
