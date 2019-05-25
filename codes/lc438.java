import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=438 lang=java
 *
 * [438] 找到字符串中所有字母异位词
 * 
 * 1、暴力：设置p.length()的窗口在s上滑动，判断窗口中的字符串与p是否为字母异位词,不能跳过得一个一个滑动(很难跳过？)
 * 2、窗口和双指针(可以跳过，一次滑动很多)
 */
class Solution {
    public List<Integer> findAnagrams1(String s, String p) {
        int lenP = p.length();
        if (s == null || lenP > s.length()) {
            return new ArrayList<>();
        }
        int[] times = new int[26];
        for (char chr : p.toCharArray()) {
            times[chr - 'a']++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= s.length() - lenP; ++i) {
            if (findOneAnagram(s,times, i, lenP)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean findOneAnagram(String s, int[] touchedP, int index, int len) {
        int[] touchedS = new int[26];
        for (int i = index; i < index + len; ++i) {
            touchedS[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; ++i) {
            if (touchedS[i] != touchedP[i]) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] timesP = new int[26];
        for (char chr : p.toCharArray()) {
            timesP[chr - 'a']++;
        }
        int start = 0, end = 0; // 窗口中的两个指针
        int[] timesBetween = new int[26]; // 记录两指针之间的字符出现次数
        while (end < s.length()) {
            // 注册s串中当前字符
            int curChrIndex = s.charAt(end++) - 'a'; 
            timesBetween[curChrIndex]++;
            // 窗口中s串的当前字符数量大于p串，说明该窗口组成的字符串与p不是字母异位词，重置窗口
            while (timesBetween[curChrIndex] > timesP[curChrIndex]) {
                timesBetween[s.charAt(start++) - 'a']--; // 当前元素若是非p中元素，则最后start在当前不同元素之后；当前元素若是p中元素，则最后start在当前窗口中第一个该元素之上
            }
            // 注册s串中当前字符时end++，所以end - start为p.length时窗口组成的字符串与p是字母异位词
            if (end - start == p.length()) {
                res.add(start);
            }
        }
        return res;
    }

}

