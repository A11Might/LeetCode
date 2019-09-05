import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=438 lang=java
 *
 * [438] 找到字符串中所有字母异位词
 * 
 * 题目：找到给定字符串s中所有非空字符串p的字母异位词的子串，返回这些子串的起始索引
 * 
 * 思路：1、固定滑动窗口，设置p.length()的窗口在s上滑动，判断窗口中的字符串与p是否为字母异位词,不能跳过得一个一个滑动(很难跳过？)
 *       2、滑动窗口，维持窗口中的字母不超过p，字母种类，单一字母个数(可以跳过，一次滑动很多)
 */
class Solution {
    public List<Integer> findAnagrams1(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] times = new int[26];
        for (char chr : p.toCharArray()) {
            times[chr - 'a']++;
        }
        int[] curTimes = new int[26];
        int l = 0, r = -1;
        while (r + 1 < s.length()) {
            curTimes[s.charAt(++r) - 'a']++;
            // 窗口固定为p的长度
            if (r - l + 1 > p.length()) {
                curTimes[s.charAt(l++) - 'a']--;
            }
            // 判断每个窗口中的字符串与p是否为字母异位词
            if (r - l + 1 == p.length() && same(times, curTimes)) {
                res.add(l);
            }
        }
        
        return res;
    }
    
    private boolean same(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        
        return true;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] times = new int[26];
        for (char chr : p.toCharArray()) {
            times[chr - 'a']++;
        }
        int[] curTimes = new int[26];
        int l = 0, r = -1;
        while (r + 1 < s.length()) {
            char cur = s.charAt(++r);
            curTimes[cur - 'a']++;
            // 维持窗口中的字母不超过p
            // 当窗口中的当前字母个数超过p时，通过移动左边界来去除该多余字母
            while (curTimes[cur - 'a'] > times[cur - 'a']) {
                curTimes[s.charAt(l++) - 'a']--;
            }
            // 形成窗口，记录字母异位词起始索引
            if (r - l + 1 == p.length()) {
                res.add(l);
            }
        }
        
        return res;
    }
}

