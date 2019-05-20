import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 * 
 * 1、当且仅当它们的排序字符串相等时，两个字符串是字母异位词
 * 2、当且仅当它们的字符计数（每个字符的出现次数）相同时，两个字符串是字母异位词
 */
class Solution {
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chrsOfS = s.toCharArray();
            Arrays.sort(chrsOfS);
            String key = String.valueOf(chrsOfS);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        HashMap<String, List<String>> map = new HashMap<>();
        int[] times = new int[26];
        for (String s : strs) {
            Arrays.fill(times, 0); // 每次用完数组需要重置一下
            for (char chr : s.toCharArray()) {
                times[chr - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < times.length; ++i) {
                sb.append("#");
                sb.append(times[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}

