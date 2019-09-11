import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 * 
 * 题目：将给定字符串数组中字母异位词组合在一起
 * 
 * 思路：查找表map,
 *       1、use sort string as key
 *       2、use char's frequence as key(所有26位字母的词频组成的字符串)
 */
class Solution {
    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chrs = str.toCharArray();
            Arrays.sort(chrs);
            String key = String.valueOf(chrs);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    private String getKey(String str) {
        int[] freq = new int[26];
        for (char chr : str.toCharArray()) {
            freq[chr - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : freq) {
            sb.append(num + "#");
        }

        return sb.toString();
    }
}

