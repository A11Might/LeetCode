/*
 * @lc app=leetcode.cn id=9 lang=java
 *
 * [9] 回文数
 * 
 * 题意：判断一个整数是否是回文数
 * 
 * 思路：使用逐步取余的方法反转整数，若反转后的整数等于原整数则为true，否则false
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false; // 小于0，含有符号肯定不是回文数

        int unreverseX = x;
        int reverseX = 0;
        // 逐步取余的方法反转整数
        while (x != 0) {
            reverseX = reverseX * 10 + x % 10;
            x /= 10;
        }

        return reverseX == unreverseX;
    }
}

