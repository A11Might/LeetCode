/**
 * @author qhhu
 * @date 2020/3/3 - 9:44
 *
 * [172] 阶乘后的零
 *
 * 题目: 返回给定整数 n 的阶乘结果尾数中零的数量.
 *
 * 难度: easy
 *
 * 思路: 尾部的 0 由 2 * 5 得来, 2 的数量明显多于 5 的数量, 因此只要统计有多少个 5 即可.
 *      对于一个数 n, 它所包含 5 的个数为: n / 5 + n / 5 ^2 + n / 5 ^3 + .... 其中 n / 5 表示不大于 n 的数中 5 的倍数贡献一个 5,
 *      n / 5 ^2 表示不大于 n 的数中 5 ^2 的倍数再贡献一个 5 ....
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int trailingZeroes(int n) {
        int cnt = 0;
        while (n >= 5) {
            cnt += n / 5;
            n /= 5;
        }

        return cnt;
    }
}