/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 * 
 * 题目：在给定字符串s中找到包含字符串t所有字母的最小子串
 *      (如果 S 中不存这样的子串，则返回空字符串 ""，如果 S 中存在这样的子串，我们保证它是唯一的答案)
 * 
 * 思路：https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-2/(方法二看看)
 *      滑动窗口，通过移动right指针不断扩张窗口。当窗口包含全部所需的字符后，移动left指针收缩窗口，找到包含t中所有字母的最小窗口，
 *      然后再次收缩窗口，移除窗口最前面的t中字母，再移动right指针扩大窗口，继续寻找找到其他子串               
 */
class Solution {
    public String minWindow(String s, String t) {
        int[] tTimes = new int[128];
        for (char chr : t.toCharArray()) {
            tTimes[chr]++;
        }
        int[] windowTimes = new int[128];
        int l = 0, r = -1; // 滑动窗口[l, r]
        int minLen = s.length() + 1; // 最小子串长度
        int start = -1; // 最小子串开始位置
        int count = 0; // 窗口中含有t字符串中字母的个数(用于判断子串形成与否)
        while (r + 1 < s.length()) {
            // 向右扩大窗口
            char curR = s.charAt(++r);
            windowTimes[curR]++;
            // 如果添加的当前字符的频率向t中的期望计数趋近，则将窗口中含有t字符串中字母的个数增加1
            if (windowTimes[curR] <= tTimes[curR]) {
                count++;
            } 

            // 找到一个子串
            while (l <= r && count == t.length()) {
                // 更新最小子串信息
                if (minLen > r - l + 1) {
                    minLen = r - l + 1;
                    start = l;
                }
                // 试图缩小窗口来，寻找更小的子串
                char curL = s.charAt(l++);
                windowTimes[curL]--;
                if (windowTimes[curL] < tTimes[curL]) {
                    count--;
                } 
            }
        }

        if (start == -1) {
            return "";
        }

        return s.substring(start, start + minLen);
    }
}

