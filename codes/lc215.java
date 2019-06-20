import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 * 
 * 题目：在未排序的数组中找到第 k 个最大的元素
 * 
 * 思路：
 * 1、大小为k的小根堆
 * 2、bfprt算法
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 处理k < 1 || k > nums.length的特殊情况
        if (k < 1 || k > nums.length) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            if (k < 1) return max;
            if (k > nums.length) return min;
        }
        Queue<Integer> smallHeap = new PriorityQueue<>(k, new smallHeapComparator()); // 定义大小为k的小根堆
        // 先将数组前k个元素加入小根堆
        for (int i = 0; i != k; ++i) {
            smallHeap.add(nums[i]);
        }
        // 再将数组其余元素加入小根堆，维持小根堆大小为k
        for (int i = k; i != nums.length; ++i) {
            int curSmall = smallHeap.peek();
            if (curSmall < nums[i]) {
                smallHeap.poll();
                smallHeap.add(nums[i]);
            }
        }

        return smallHeap.poll(); 
    }

    // 小根堆比较器
    public class smallHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }

    }
}

