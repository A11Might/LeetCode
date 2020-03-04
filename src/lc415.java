/**
 * @author qhhu
 * @date 2020/3/3 - 9:45
 *
 * [415] 字符串相加
 *
 * 题目: 给定两个字符串形式的非负数, 计算它们的和.
 *
 * 难度: easy
 *
 * 思路: 依次从低到高读取字符串中的数进行相加.
 */
class Solution {
    /**
     * 时间复杂度: O(max(m, n)) (m, n 为输入字符串 num1, nums2 的长度)
     * 空间复杂度: O(max(m, n))
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int sum = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += num2.charAt(j--) - '0';
            }
            carry = sum / 10;
            sb.append(sum % 10);
        }

        return sb.reverse().toString();
    }
}