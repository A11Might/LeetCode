/*
 * @lc app=leetcode.cn id=557 lang=java
 *
 * [557] 反转字符串中的单词 III
 * 
 * 题意：反转字符串中每个单词的字符顺序
 * 
 * 思路：
 * 1、按题意做
 * 2、双指针，遇到空格时反转两指针中的字符(解耦函数可以，别解耦太多好像反而会变慢)
 */
class Solution {
    public String reverseWords1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        StringBuilder sb  = new StringBuilder();
        String[] strs = s.split(" ");

        // 反转每个单词的字符顺序
        for (int i = 0; i < strs.length; ++i) {
            char[] chrs = strs[i].toCharArray();
            int left = 0, right = chrs.length - 1;
            while (left < right) {
                swap(chrs, left++, right--);
            }
            strs[i] = String.valueOf(chrs);
        }

        for (String str : strs) {
            sb.append(str + " ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private void swap(char[] chrs, int a, int b) {
        char temp = chrs[b];
        chrs[b] = chrs[a];
        chrs[a] = temp;
    }

    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        char[] chrs = s.toCharArray();
        int left = 0, right = 0;
        while (right < chrs.length) {
            if (chrs[right] == ' ') {
                swapChars(chrs, left, right - 1); // 反转当前遍历到的单词
                left = right + 1;
                right = left;
            }
            right++;
        }
        swapChars(chrs, left, right - 1); // 反转最后一个单词(最后一个单词没有' '触发反转)

        return new String(chrs);
    }

    // 返回两索引间的所有字符
    private void swapChars(char[] chrs, int left, int right) {
        while (left < right) {
            char temp = chrs[right];
            chrs[right] = chrs[left];
            chrs[left] = temp;
            left++;
            right--;
        }
    }
}

