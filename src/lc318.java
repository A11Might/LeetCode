/**
 * @author qhhu
 * @date 2020/2/29 - 9:16
 *
 * [318] 最大单词长度乘积
 *
 * 题目: 字符串数组的字符串只含有小写字符. 求解字符串数组中两个字符串长度的最大乘积, 要求这两个字符串不能含有相同字符.
 *
 * 难度: medium
 *
 * 思路: 判断两个字符串是否含相同字符, 由于字符串只含有小写字符, 总共 26 位, 因此可以用一个 32 位的整数来存储每个字符是否出现过.
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] val = new int[len];
        for (int i = 0; i < len; i++) {
            // 使用 32 为整数来存储单词中每个字符是否出现过.
            for (char chr : words[i].toCharArray()) {
                val[i] |= 1 << (chr - 'a');
            }
        }

        int maxLen = 0;
        // 判断两个字符串是否有相同字符, 若没有则更新最大乘积.
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((val[i] & val[j]) == 0) {
                    maxLen = Math.max(maxLen, words[i].length() * words[j].length());
                }
            }
        }

        return maxLen;
    }
}