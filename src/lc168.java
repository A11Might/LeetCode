/**
 * @author qhhu
 * @date 2020/3/2 - 9:51
 *
 * [168] Excel 表列名称
 *
 * 题目：返回正给定整数在 Excel 表中相对应的列名称。
 *
 * 难度：easy
 *
 * 思路：十进制转二十六进制，因为是从 1 开始计算的, 而不是从 0 开始, 因此需要对每一位的 n 执行 -1 操作，再进行转换。
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(logn)
     */
    public String convertToTitle(int n) {
        String ret = "";
        while (n != 0) {
            ret = (char)((n - 1) % 26 + 'A') + ret;
            n = (n - 1) / 26; // 注意 corner case: n = 26
        }
        return ret;
    }
}