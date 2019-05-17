/*
 * @lc app=leetcode.cn id=14 lang=java
 *
 * [14] 最长公共前缀
 * 
 * https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode/
 * 1、2、暴力，横向或纵向
 * 3、分治，类似归并排序
 * 4、二分
 * 5、前缀树
 */
class Solution {
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = l + ((r - l) >> 1);
            String leftString = longestCommonPrefix(strs, l, mid);
            String rightString = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(leftString, rightString);
        }
    }

    private String commonPrefix(String l, String r) {
        int min = Math.min(l.length(), r.length());
        for (int i = 0; i < min; ++i) {
            if (l.charAt(i) != r.charAt(i)) {
                return l.substring(0, i);
            }
        }
        return l.substring(0, min);
    }
}

