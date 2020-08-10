import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 * 
 * 题目：在未排序的数组中找到第 k 大的元素
 *
 * 难度: medium
 * 
 * 思路：1. 使用大小为 k 的小根堆
 *      2. 利用快排 partition 分割数组，分割位置为 n - k 时，找到第 k 个最大的元素
 *      3. bfprt 算法
 */
class Solution {
    /**
     * 时间复杂度：O(n * logk)
     * 空间复杂度：O(k)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // 默认为小根堆
        // 将数组中的元素加入到小根堆中
        for (int num : nums) {
            heap.add(num);
            // 维持小根堆大小为 k
            if (heap.size() > k) heap.poll();
        }

        return heap.peek();
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k + 1); // 注意题目给的 k 是排序后从后往前数的
    }
    
    private int quickSort(int[] nums, int l, int r, int k) {
        if (l >= r) return nums[l];
        
        int i = l - 1, j = r + 1, x = nums[l + r >> 1];
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        int leftSize = j - l + 1;
        if (k <= leftSize) return quickSort(nums, l, j, k);
        return quickSort(nums, j + 1, r, k - leftSize);
    }
}

