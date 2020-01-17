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
    private HashSet<Character> dict = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public String reverseVowels(String s) {
        if (s == null) return null;
        int left = 0, right = s.length() - 1;
        char[] chrs = s.toCharArray(); // 这个应该不算额外空间
        while (left < right) {
            char chrL = chrs[left], chrR = chrs[right];
            if (!dict.contains(chrL)) {
                // 当前左指针位置字符不为元音字母
                left++;
            } else if (!dict.contains(chrR)) {
                // 当前右指针位置字符不为元音字母
                right--;
            } else {
                // 当前左右指针位置字符都为元音字母, 反转两元音字母
                char temp = chrs[right];
                chrs[right--] = chrs[left];
                chrs[left++] = temp;
            }
        }

        return new String(chrs);
    }
}

