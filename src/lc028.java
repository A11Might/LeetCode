/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现strStr()
 * 
 * 1、暴力：找到needle[0]在haystack中的位置i，开始比较haystack[i:]和neddle
 * 2、优化暴力算法
 * 3、KMP，不会
 */
class Solution {
    public int strStr2(String haystack, String needle) {
        if (needle == null || needle.length() < 1) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int j = 0;
        for (int i = 0; i < haystack.length(); ++i) {
            // 主串与模式串对应字符相等，开始比较；不等重新比较
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
            // 模式串比较完，返回在主串中的开始下标
            if (j == needle.length()) {
                return i - (needle.length() - 1);
            }
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() < 1) {
            return 0;
        }
        for (int i = 0; i < haystack.length(); ++i) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = i + 1, k = 1;
                while (j < haystack.length() && k < needle.length()) {
                    if (haystack.charAt(j) != needle.charAt(k)) {
                        break;
                    }
                    j++;
                    k++;
                }
                if (k == needle.length()) {
                    return i;
                }
            }
        }
        return -1;
    }
}

