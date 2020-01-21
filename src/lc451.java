import java.util.*;

/*
 * @lc app=leetcode.cn id=451 lang=java
 *
 * [451] 根据字符出现频率排序
 * 
 * 题目: 将给定字符串中的字符按照出现频率降序排列
 *
 * 难度: medium
 * 
 * 思路: 查找表map, 1. 堆排序
 *               2. 桶排序
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(n)
     */
    public String frequencySort(String s) {
        HashMap<Character, Integer> freq = new HashMap<>(); // (char, time)
        // 统计每个字符的出现频率
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            freq.put(chr, freq.getOrDefault(chr, 0) + 1);
        }

        // 按出现频率排序字母
        PriorityQueue<Character> queue = new PriorityQueue<>(
                (o1, o2) -> freq.get(o2) - freq.get(o1)
        );
        freq.forEach(
                (k, v) -> queue.offer(k)
        );

        // 按格式输出
        StringBuilder sb = new StringBuilder();
        // 优先队列只能保证队首元素符合比较器, 当队首元素弹出时新的队首才会满足比较器(debug发现的)
        // 所以若使用foreach生成stream, stream中的元素并不是按照比较器排列的, 所以需要一个一个弹出遍历
        while (!queue.isEmpty()) {
            char chr = queue.poll();
            for (int i = 0; i < freq.get(chr); i++) {
                sb.append(chr);
            }
        }
        return sb.toString();
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public String frequencySort2(String s) {
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        for (char c : s.toCharArray())
            frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);

        List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
        for (char c : frequencyForNum.keySet()) {
            int f = frequencyForNum.get(c);
            if (frequencyBucket[f] == null) {
                frequencyBucket[f] = new ArrayList<>();
            }
            frequencyBucket[f].add(c);
        }
        StringBuilder str = new StringBuilder();
        for (int i = frequencyBucket.length - 1; i >= 0; i--) {
            if (frequencyBucket[i] == null) {
                continue;
            }
            for (char c : frequencyBucket[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }
}
