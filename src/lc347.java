import java.util.*;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前K个高频元素
 * 
 * 题目：返回非空整数数组出现频率前k高的元素, 要求时间复杂度优于O(n * logn)
 *
 * 难度：medium
 * 
 * 思路：1. 使用HashMap统计每个元素出现次数, 再用大小为k个小根堆获取前k个高频元素, 时间复杂度O(n + n * logk)
 *      2. 桶排序, 设置若干个桶, 每个桶存储出现频率相同的数. 桶的下标表示数出现的频率, 即第 i 个桶中存储的数出现的频率为 i.
 *                把数都放到桶之后, 从后向前遍历桶, 最先得到的 k 个数就是出现频率最多的的 k 个数
 */
class Solution {
    /**
     * 时间复杂度: O(n * logk)
     * 空间复杂度: O(n)
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>(); // (element, time)
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (o1, o2) -> freq.get(o1) - freq.get(o2) // 小根堆, 比较的是元素的词频
        );
        // 将哈希表中的元素加入到小根堆中
        freq.forEach(
                (key, value) -> {
                    heap.add(key);
                    // 维持小根堆大小为k
                    if (heap.size() > k) {
                        heap.poll();
                    }
                }
        );

        return heap.stream().collect(Collectors.toList());
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        return topK;
    }
}