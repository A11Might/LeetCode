import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前K个高频元素
 * 
 * 题目：返回非空整数数组数显频率前k高的元素，要求时间复杂度优于O(n * logn)
 * 
 * 思路：使用HashMap统计每个元素出现次数，再用大小为k个小根堆获取前k个高频元素，时间复杂度O(n + n * logk)
 */
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // (num, times)
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2); // 比较的是元素的词频
            } 
        });
        for (int key : map.keySet()) {
            if (smallHeap.size() < k) {
                smallHeap.add(key);
            } else if (map.get(smallHeap.peek()) < map.get(key)) { // 比较的是元素的词频
                smallHeap.poll();
                smallHeap.add(key);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!smallHeap.isEmpty()) {
            res.add(smallHeap.poll());
        }
        return res;
    }
}

