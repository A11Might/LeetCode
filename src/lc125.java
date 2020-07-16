/*
 * @lc app=leetcode.cn id=125 lang=java
 *
 * [125] 验证回文串
 *
 * 题目：判断给定字符串是否是回文串
 *      （只考虑字母和数字字符，忽略字母的大小写）
 *
 * 难度：easy
 *
 * 思路：使用两个指针分别指向字符串的开头和结尾，每次判断当前指针指向的字母或者数字是否相同，判断完后都向中间移动一位，如果当前指针指向的不是字母或数字则跳过。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            while (i < j && !check(s.charAt(i))) i++;
            while (j > i && !check(s.charAt(j))) j--;
            if (s.charAt(i) != s.charAt(j)
                    // 在相同字母的大小写的二进制表示里，只有第5位不同，异或32可以起到只改变第5位的效果，从而实现大小写的转化
                    && s.charAt(i) != (s.charAt(j) ^ 32)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(char chr) {
        if ((chr >= '0' && chr <= '9')
                || (chr >= 'a' && chr <= 'z')
                || (chr >= 'A' && chr <= 'Z')) {
            return true;
        }
        return false;
    }
}