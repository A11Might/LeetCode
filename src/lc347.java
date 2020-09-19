import java.util.*;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前K个高频元素
 * 
 * 题目：返回非空整数数组出现频率前k高的元素，要求时间复杂度优于 O(n * logn)
 *
 * 难度：medium
 * 
 * 思路：使用 HashMap 统计每个元素出现次数
 *      1. 用大小为 k 个小根堆获取前 k 个高频元素，时间复杂度 O(n + n * logk) 优于 O(n * logn)
 *      2. 计数排序，设置若干个桶，每个桶存储出现频率相同的数（桶的下标表示数出现的频率，即第 i 个桶中存储的数出现的频率为 i）
 *                  把数都放到桶之后，从后向前遍历桶，最先得到的 k 个数就是出现频率最多的的 k 个数
 */
class Solution {
    /**
     * 时间复杂度: O(n * logk)
     * 空间复杂度: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>(); // <num, times>
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (o1, o2) -> freq.get(o1) - freq.get(o2)); // 小根堆，比较的是元素的词频
        // 将哈希表中的元素加入到小根堆中
        freq.forEach(
            (num, times) -> {
                heap.offer(num);
                // 维持小根堆大小为 k
                if (heap.size() > k) heap.poll();
            });
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) ret[i] = heap.poll();
        return ret;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>(); // <num, times>
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        int n = nums.length;
        // 把数都放到桶中（桶的下标表示数出现的频率，即第 i 个桶中存储的数出现的频率为 i）
        List<Integer>[] buckets = new ArrayList[n + 1];
        freq.forEach(
            (num, times) -> {
                if (buckets[times] == null) buckets[times] = new ArrayList<>();
                buckets[times].add(num);
            });
        // 从后向前遍历桶，最先得到的 k 个数就是出现频率最多的的 k 个数
        int[] ret = new int[k];
        for (int i = n, idx = 0; i >= 0 && idx < k; i--) {
            if (buckets[i] == null) continue;
            for (int num : buckets[i]) {
                ret[idx++] = num;
                if (idx == k) break;
            }
        }
        return ret;
    }
}