/**
 * @author qhhu
 * @date 2019/12/6 - 15:35
 *
 * [263] 丑数
 *
 * 题目: 返回给定的数是否是丑数
 *      (丑数就是只包含质因子2, 3, 5的正整数)
 *
 * 难度: easy
 *
 * 思路: 丑数 = 1 * (i * 2) * (j * 3) * (k * 5) i, j, k >= 0
 *       对能被2,3,5整除的数不断除2,3,5，最后剩1就是，剩0就不是
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public boolean isUgly(int num) {
        if (num < 1) return false;
        while ((num & 1) == 0) num >>= 1;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;

        return num == 1;
    }
}
