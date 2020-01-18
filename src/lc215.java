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
 * 思路：1, 大小为k的小根堆
 *      2, 利用快排partition分割数组, 分割位置为n - k时, 找到第k个最大的元素
 *      3, bfprt算法
 */
class Solution {
    /**
     * 时间复杂度: O(n * logk)
     * 空间复杂度: O(k)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (num1, num2) -> num1 - num2
        ); // 定义小根堆
        // 将数组中的元素加入到小根堆中
        for (int num : nums) {
            heap.add(num);
            // 维持小根堆大小为k
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.peek();
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        int target = len - k; // 目标元素的索引
        int left = 0, right = len - 1;
        // 在数组中寻找位置为target的pivot
        while (left <= right) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[target];
            } else if (index > target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }

        throw new IllegalArgumentException("no solution");
    }

    // 返回pivot位置的索引
    private int partition(int[] nums, int left, int right) {
        int len = right - left + 1;
        swap(nums, right, left + (int) Math.random() * len); // 随机选取一个元素作为partition的pivot
        int small = left; // 将区间分为 <= 和 > 两部分
        while (left < right) {
            if (nums[left] <= nums[right]) {
                swap(nums, small++, left);
            }
            left++;
        }
        swap(nums, small, right);
        return small;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

