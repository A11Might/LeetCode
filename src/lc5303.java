/**
 * @author qhhu
 * @date 2020/1/5 - 15:53
 *
 * [5303] 解码字母到整数映射
 *
 * 题目: 给定一个由数字['0', '9']和'#'组成的字符串, 按照['1', '9']表示['a', 'i'], ['10#', '26#']表示['j', 'z']的规则
 *      将s映射成新的字符串, 题目数据保证映射始终唯一
 *
 * 难度: easy
 *
 * 思路: 从后往前遍历字符串, 遇到'#'则向前取两个字符进行转换, 否则直接转换这个字符
 */
class Solution {
    public String freqAlphabets(String s) {
        char[] chrs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chrs.length - 1; i >= 0; i--) {
            int value = 0;
            if (chrs[i] == '#') {
                value = Integer.parseInt(s.substring(i - 2, i));
                sb.append((char) ('a' + value - 1));
                i -= 2; // 记得要向前跳两个字符
            } else {
                value = chrs[i] - '0';
                sb.append((char) ('a' + value - 1));
            }
        }
        return sb.reverse().toString();
    }
}
