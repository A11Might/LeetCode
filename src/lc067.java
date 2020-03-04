/**
 * @author qhhu
 * @date 2020/3/3 - 9:45
 *
 * [67] 二进制求和
 *
 * 题目: 给定两个二进制字符串, 返回它们的和(用二进制表示).
 *
 * 难度: easy
 *
 * 思路: 依次从低到高读取二进制位上的数进行相加.
 */
class Solution {
    /**
     * 时间复杂度: O(max(m, n)) (m, n 为输入字符串 a, b 的长度)
     * 空间复杂度: O(max(m, n))
     */
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        int sum = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            sum = carry;
            if (i >= 0 && a.charAt(i--) == '1') {
                sum++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                sum++;
            }
            carry = sum / 2;
            sb.append(sum % 2);
        }

        return sb.reverse().toString();
    }
}