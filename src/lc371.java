/**
 * @author qhhu
 * @date 2020/2/29 - 9:16
 *
 * [371] 两整数之和
 *
 * 题目: 不使用运算符 '+' 和 '-', 计算量整数 a, b 之和.
 *
 * 难度: easy
 *
 * 思路: sum = a ^ b 表示没有考虑进位的情况下两数的和, carry= (a & b) << 1 表示两数之和的进位.
 *      每次相加都会出现一组 sum 和 carry, 再用相同的方法将它们相加, 直至 carry == 0, 则 sum 即为两数之和.
 */
class Solution {
    /**
     * 时间复杂度: O()
     * 空间复杂度: O()
     */
    public int getSum(int a, int b) {
        int sum = 0, carry = 0;
        do {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        } while (carry != 0);

        return sum;
    }
}