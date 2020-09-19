import java.util.Arrays;
import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=345 lang=java
 *
 * [345] 反转字符串中的元音字母
 *
 * 题目：反转给定字符串中的元音字母
 *
 * 难度: easy
 *
 * 思路：指针碰撞/双指针, 类似[125]
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    private HashSet<Character> dict = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    
    public String reverseVowels(String s) {
        char[] chrs = s.toCharArray();
        for (int i = 0, j = chrs.length - 1; i < j; i++, j--) {
            while (i < j && !dict.contains(chrs[i])) i++;
            while (i < j && !dict.contains(chrs[j])) j--;

            char tmp = chrs[j];
            chrs[j] = chrs[i];
            chrs[i] = tmp;
        }
        return new String(chrs);
    }
}