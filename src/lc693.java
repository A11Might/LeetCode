/**
 * @author qhhu
 * @date 2020/2/29 - 9:16
 *
 * [693] 交替位二进制数
 *
 * 题目: 检查给定正整数是否为交替位二进制数.
 *      (交替位二进制数二进制数相邻的两个位数永不相等.)
 *
 * 难度: easy
 *
 * 思路: 对于 1010 这种位级表示的数, 把它向右移动 1 位得到 101, 这两个数每个位都不同, 因此异或得到的结果为 1111.
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public boolean hasAlternatingBits(int n) {
        int ret = n ^ (n >> 1);
        return (ret & (ret + 1)) == 0;
    }
}