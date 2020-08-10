import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=205 lang=java
 *
 * [205] 同构字符串
 * 
 * 题目：判断给定的两个字符串 s, t 是否是同构的
 *      （如果 s 中的字符可以被替换得到 t，那么这两个字符串是同构的）
 *      （所有出现的字符都必须用另一个字符替换，同时保留字符的顺序；两个字符不能映射到同一个字符上，但字符可以映射自己本身）
 *
 * 难度：easy
 * 
 * 思路：1. 查找表 map（可以用数组代替），遍历字符串 s，注册当前 s 中字符对应 t 中的字符，同时检查是否为正确同构（注意，两个字符不能映射到同一个字符上）
 *      2. 记录当前字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) return false;
        int[] map = new int[128]; // map[char of s] = char of t
        boolean[] mapped = new boolean[128]; // char of t 是否映射过
        Arrays.fill(map, -1);
        for (int i = 0; i < sLen; i++) {
            char chrS = s.charAt(i), chrT = t.charAt(i);
            // 当前字母注册过，判断映射正确与否
            if (map[chrS] != -1) {
                if (map[chrS] != chrT) {
                    return false;
                }
            } else {
                // 当前字母未注册过，注册前判断是否会造成两个字符映射到同一个字符上了
                if (mapped[chrT]) {
                    return false;
                }
                map[chrS] = chrT;
                mapped[chrT] = true;
            }
        }

        return true;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isIsomorphic2(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) return false;
        int[] preIndexOfS = new int[128];
        int[] preIndexOfT = new int[128];
        for (int i = 0; i < sLen; i++) {
            int chrS = s.charAt(i), chrT = t.charAt(i);
            if (preIndexOfS[chrS] != preIndexOfT[chrT]) {
                return false;
            }
            // 数组中的值初始化为 0，i 的起始值也是 0，为了防止出错，将索引设置为 i + 1
            preIndexOfS[chrS] = preIndexOfT[chrT] = i + 1;
        }

        return true;
    }
}