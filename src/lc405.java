/**
 * @author qhhu
 * @date 2020/3/2 - 9:51
 *
 * [405] 数字转化为十六进制数
 *
 * 题目: 将给定整数转化为十六进制数. 对于负整数, 使用补码运算.
 *
 * 难度: easy
 *
 * 思路: 使用位运算, 每 4 位, 对应 1 位 16 进制数字.
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 0b1111]);
            num >>>= 4;
        }

        return sb.reverse().toString();
    }
}