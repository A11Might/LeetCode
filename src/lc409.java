/**
 * @author qhhu
 * @date 2020/2/12 - 11:33
 *
 * [409] 最长回文串
 *
 * 题目: 给定一个包含大写字母和小写字母的字符串, 返回通过这些字母构成的最长的回文串.
 *      ("Aa"不是回文串)
 *
 * 难度: easy
 *
 * 思路: 贪心策略, 对于每个字母, 假设它出现了cnt次. 我们就可以让cnt / 2 * 2个字母左右对称.
 *              需要额外判断, 是否有字符是回文串中唯一的中心
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int longestPalindrome(String s) {
        int[] cnts = new int[256];
        for (char chr : s.toCharArray()) {
            cnts[chr - 'A']++;
        }

        int ret = 0;
        for (int cnt : cnts) {
            ret += cnt / 2 * 2;
        }
        // 对于中心的处理, 只要字符串长度是大于结果的, 说明一定有不成对出现的, 加一返回结果即可
        if (ret < s.length()) {
            ret++;
        }

        return ret;
    }
}
