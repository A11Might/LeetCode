/*
 * @lc app=leetcode.cn id=316 lang=java
 *
 * [316] 去除重复字母
 * 
 * 题意：去除只包含小写字母的字符串中重复的字母，使返回结果的字典序最小(不能打乱其他字符的相对位置)
 * 
 * https://www.bilibili.com/video/av55202842
 * 思路：遍历字符串中每个字符，若当前字符比其前面字符小并且前面字符在当前字符后还有出现，则删除当前字符前的字符
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        StringBuilder sb = new StringBuilder(); // 返回值
        int[] count = new int[26]; // 字符串中每个字符出现次数
        boolean[] used = new boolean[26]; // 是否已经加入返回值中

        char[] chrs = s.toCharArray();
        // 统计字符串中每个字符出现次数
        for (char chr : chrs) {
            count[chr - 'a']++;
        }

        for (char chr : chrs) {
            // 取出当前字符，字符串中字符出现次数减一
            count[chr - 'a']--;
            // 返回值中出现过，则跳过
            // 对应情况aaab、abaa的第2、3个a；babb情况当遍历到重复b时字符串为abb，最前面的字符b已被删除
            if (used[chr - 'a']) continue; 

            // 若当前字符比其前面字符小并且前面字符在当前字符后还有出现，则删除当前字符前的字符
            while (sb.length() > 0 
                    && sb.charAt(sb.length() - 1) > chr 
                    && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                used[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(chr);
            used[chr - 'a'] = true;
        }

        return sb.toString();
    }
}

