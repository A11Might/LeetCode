/*
 * @lc app=leetcode.cn id=125 lang=java
 *
 * [125] 验证回文串
 * 
 * 1、指针碰撞，两个指针跳过不是小写字母和数字的字符
 * 2、使用while加速跳过(连续跳过多个非法字符) 快一倍
 */
class Solution {
    public boolean isPalindrome2(String s) {
        if (s == null) {
            return true;
        }
        s = s.toLowerCase();
        char[] chrsS = s.toCharArray();
        int l = 0, r = chrsS.length - 1;
        while (l < r) { // 小于即可，奇数个字符时最中间字符不用比较
            while (l < chrsS.length && !isValid(chrsS, l)) {
                l++;
            }
            while (r >= 0 && !isValid(chrsS, r)) {
                r--;
            }
            // 比较前后字符后不移动指针，会进入死循环
            if (l < r && chrsS[l++] != chrsS[r--]) {
                    return false;
            }
        }
        
        return true;
    }

    private boolean isValid(char[] chrs, int index) {
        // 使用字符asicII码判断字符是否是字母和数字
        if (chrs[index] >= 'a' && chrs[index] <= 'z' || chrs[index] >= '0' && chrs[index] <= '9') {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        s = s.toLowerCase();
        char[] chrsS = s.toCharArray();
        int l = 0, r = chrsS.length - 1;
        while (l < r) {
            if (!isValid(chrsS, l)) {
                l++;
            } else if (!isValid(chrsS, r)) {
                r--;
            } else if (isValid(chrsS, l) && isValid(chrsS, r)) {
                if (chrsS[l++] != chrsS[r--]) {
                    return false;
                }
            }
        }
        return true;
    }
}

