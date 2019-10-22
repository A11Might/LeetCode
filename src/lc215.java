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
 * 2、利用快排partition分割数组，分割位置为n - k时，找到第k个最大的元素
 * 3、bfprt算法
 */
class Solution {
    public int findKthLargest1(int[] nums, int k) {
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
    
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k || k == 0) {
            return 0;
        }
        int n = nums.length;
        int target = n - k; // 目标元素的索引
        // 在数组中寻找位置为target的pivot
        int start = 0, end = n - 1;
        int index = partition(nums, start, end);
        while (index != target) {
            if (index < target) {
                start = index + 1;
            } else {
                end = index - 1;
            }
            index = partition(nums, start, end);
        }
        
        return nums[index];
    }
    
    // 返回pivot位置的索引
    private int partition(int[] arr, int left, int right) {
        int n = right - left + 1;
        swap(arr, right, left + (int) Math.random() * n);
        int pivot = arr[right];
        int small = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, ++small);
            }
        }
        swap(arr, right, ++small);
        return small;
    }
    
    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

