import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=451 lang=java
 *
 * [451] 根据字符出现频率排序
 * 
 * 题目：将给定字符串中的字符按照出现频率降序排列
 * 
 * 思路：查找表map
 */
class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 统计每个字符的出现频率
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }

        // 按出现频率排序字母
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(entry);
        }

        // 按格式输出
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> curEntry = queue.poll();
            for (int i = 0; i < curEntry.getValue(); i++) {
                sb.append(curEntry.getKey());
            }
        }
        
        return sb.toString();
    }
}

