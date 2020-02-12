/**
 * @author qhhu
 * @date 2020/2/12 - 11:34
 *
 * [696] 计数二进制子串
 *
 * 题目: 给定一个字符串, 计算其具有相同数量0和1的非空子串的数量, 并且这些子串中所有0和所有1都是组合在一起的.
 *      (重复出现的子串要计算它们的出现次数)
 *
 * 难度: easy
 *
 * 思路: 遍历字符串, 当前字符与前一个字符相同时累计字符相同的连续快的长度;
 *                当前字符与前一个字符不同时, 则开始累计一个大小为1的新的字符连续块.
 *                在这中间, 当前一个字符连续块的长度大于当前字符连续块时, 则有一个符合题意的子串
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int countBinarySubstrings(String s) {
        int preNum = -1, curNum = 1;
        int ret = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curNum++;
            } else {
                preNum = curNum;
                curNum = 1;
            }

            // 当前一个字符连续块的长度大于当前字符连续块时, 则有一个符合题意的子串
            // 1 1 1 0 0 1 1
            //       ^
            //       |
            // 当前位置preNum == 3, curNum == 1, ret++(子串为10)
            // 1 1 1 0 0 1 1
            //         ^
            //         |
            // 当前位置preNum == 3, curNum == 2, ret++(子串1100)
            // 1 1 1 0 0 1 1
            //           ^
            //           |
            // 当前位置preNum == 2, curNum == 1, ret++(子串01)
            if (preNum >= curNum) {
                ret++;
            }
        }

        return ret;
    }
}