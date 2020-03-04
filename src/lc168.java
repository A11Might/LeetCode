/**
 * @author qhhu
 * @date 2020/3/2 - 9:51
 *
 * [168] Excel 表列名称
 *
 * 题目: 返回正给定整数在 Excel 表中相对应的列名称.
 *
 * 难度: easy
 *
 * 思路: 26 进制转换. 因为是从 1 开始计算的, 而不是从 0 开始, 因此需要对每一位的 n 执行 -1 操作.
 *      假设给定整数是 52, 其对应的列名称为 AZ, 52 = AZ = A * 26 ^1 + Z * 26 ^0, 其中 A = 1, Z = 26.
 *      现在需要将每一个字母上的数字减一, 即 A = 0, Z = 25.(我们习惯使用 26 进制, 即 A 作为第 0 个元素)
 *      所以, 52 = AZ = (A + 1) * 26 + (Z + 1).
 *      当你从 52 中寻找 A 和 Z 时, Z = (52 - 1) % 26 = 25, 需要减一.
 *      然后从 26 * (A + 1) + (Z + 1) 中计算 A, 如果只是简单地 num / 26, Z + 1 会给出一个额外的 1, 所以我们的到的是 2 而不是 1.
 *      为了避免这种情况, 也需要先减一再计算.
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(logn)
     */
    public String convertToTitle1(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n /= 26;
        }

        return sb.reverse().toString();
    }

    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(logn)
     */
    public String convertToTitle2(int n) {
        if (n == 0) return "";
        n--;
        return convertToTitle2(n / 26) + (char) (n % 26 + 'A');
    }
}