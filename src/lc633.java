/**
 * @author qhhu
 * @date 2020/1/15 - 10:01
 *
 * [633] 平方数之和
 *
 * 题目: 判断给定非负整数c, 是否等于两个整数a, b的平方和(a ^ 2 + b ^ 2 = c)
 *
 * 难度: easy
 *
 * 思路: 同[lc167], 使用双指针得到两个数, 使其平方和为c
 */
class Solution {
    /**
     * 时间复杂度: O(sqrt(c))
     * 空间复杂度: O(1)
     */
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;
        // 剪枝, 设右指针为x, 为了使平方和初始值0 ^ 2 + x ^ 2的值尽可能接近c, 可以将x取为sqrt(c)
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int powSum  = left * left + right * right;
            if (powSum  == c) {
                return true;
            } else if (powSum  > c) {
                right--;
            } else {
                left++;
            }
        }

        return false;
    }
}
