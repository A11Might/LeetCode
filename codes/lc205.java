import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=205 lang=java
 *
 * [205] 同构字符串
 * 
 * 题目：判断给定的两个字符串s、t是否是同构的
 *      (如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的)
 *      (所有出现的字符都必须用另一个字符替换，同时保留字符的顺序；两个字符不能映射到同一个字符上，但字符可以映射自己本身)
 * 
 * 思路：查找表map，可以用数组代替
 */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) {
            return false;
        }
        int[] map = new int[128]; // <char of s, char of t>
        boolean[] mapped = new boolean[128]; // char of t是否映射过
        Arrays.fill(map, -1);
        for (int i = 0; i < sLen; i++) {
            char curS = s.charAt(i);
            char curT = t.charAt(i);
            // 当前字母未注册过
            if (map[curS] == -1) {
                if (mapped[curT]) {
                    return false;
                }
                map[curS] = curT;
                mapped[curT] = true;
            // 当前字母注册过
            } else {
                if (map[curS] != curT) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

