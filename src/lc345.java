/*
 * @lc app=leetcode.cn id=345 lang=java
 *
 * [345] 反转字符串中的元音字母
 * 
 * 题目：反转给定字符串中的元音字母
 * 
 * 思路：指针碰撞，类似[125]
 */
class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chrs = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j; ) {
            // 从左边找到下一个元音字母
            while (i < s.length() && !isValid(chrs[i])) {
                i++;
            }
            // 从右边找到下一个元音字母
            while (j >= 0 && !isValid(chrs[j])) {
                j--;
            }
            // 反转元音字母
            if (i < j) {
                swap(chrs, i++, j--);
            }
        }
        
        return String.valueOf(chrs);
    }
    
    private boolean isValid(char chr) {
        if (chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u' || 
           chr == 'A' || chr == 'E' || chr == 'I' || chr == 'O' || chr == 'U' ) {
            return true;
        }
        
        return false;
    }
    
    private void swap(char[] arr, int a, int b) {
        char temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

