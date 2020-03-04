/**
 * @author qhhu
 * @date 2020/3/2 - 9:51
 *
 * [504] 七进制数
 *
 * 题目: 将给定整数转化为 7 进制并以字符串形式返回.
 *
 * 难度: easy
 *
 * 思路: 除 7 倒取余.
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(logn)
     */
    public String convertToBase71(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        boolean isNegative = num < 0;
        num = Math.abs(num);
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (isNegative) sb.append('-');

        return sb.reverse().toString();
    }

    /**
     * 时间复杂度: O()
     * 空间复杂度: O()
     */
    public String convertToBase72(int num) {
        return Integer.toString(num, 7);
    }
}