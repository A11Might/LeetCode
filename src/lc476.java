/**
 * @author qhhu
 * @date 2020/2/29 - 9:16
 *
 * [476] 数字的补数
 *
 * 题目: 返回给定整数的补数. 补数时对该数的二进制表示取反.
 *      (给定的整数保证在 32 位带符号整数的范围内; 你可以假定二进制数不包含前导零位.)
 *
 * 难度: easy
 *
 * 思路: 对于 00000101, 要求补码可以将它与 00000111 进行异或操作. 那么问题就转换为求掩码 00000111.
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public int findComplement1(int num) {
        if (num == 0) return 1;
        // 最高位为符号位, 所以只需移动 30 位.
        int mask = 1 << 30;
        while ((num & mask) == 0) mask >>= 1;
        mask = (mask << 1) - 1;
        return num ^ mask;
    }

    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public int findComplement2(int num) {
        // 获取 num 最到位的 1.
        int mask = Integer.highestOneBit(num);
        mask = (mask << 1) - 1;
        return num ^ mask;
    }

    /**
     * 对于 10000000 这样的数要扩展成 11111111, 可以利用以下方法:
     * mask |= mask >> 1    11000000
     * mask |= mask >> 2    11110000
     * mask |= mask >> 4    11111111
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public int findComplement3(int num) {
        int mask = num;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        mask |= mask >> 16;
        return (mask ^ num);
    }
}