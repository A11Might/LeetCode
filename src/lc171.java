/**
 * @author qhhu
 * @date 2020/7/31 - 11:07
 *
 * [171] Excel 表列序号
 *
 * 题目：返回给定 Excel 表格中的列名称对应的列序号
 *
 * 难度：easy
 *
 * 思路：26 进制转 10 进制
 */
class Solution {
    public int titleToNumber(String s) {
        int n = s.length();
        char[] chrs = s.toCharArray();
        int ans = 0, base = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans += (chrs[i] - 'A' + 1) * base;
            base *= 26;
        }
        return ans;
    }
}
