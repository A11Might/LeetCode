import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 * 
 * 题目：给定数组nums和滑动窗口大小k，滑动窗口从最左侧移到到最右侧，每次移动一位，返回滑动窗口中的最大值
 * 
 * 思路：维护保存当前窗口最大值下标的一个双向队列
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) { // 实例[]
            return new int[] {};
        }
        int n = nums.length;
        int l = 0, r = -1;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[n - k + 1]; // 数组中一共可以形成arr.length - w + 1个窗口
        int index = 0; // res中的索引
        while (r + 1 < n) {
            int cur = nums[++r];
            // 当前元素大于等于窗口中其前面元素时，将前面元素删除
            while (!deque.isEmpty() && nums[deque.getLast()] <= cur) {
                deque.pollLast();
            }
            deque.addLast(r);
            // 窗口的第一个元素失效，将其删除
            // r - l + 1 <= k 即 l >= r - k + 1表示l在窗口内
            if (r - k + 1 > deque.getFirst()) {
                deque.pollFirst();
            }
            // 形成窗口时开始记录窗口最大值
            if (r >= k - 1) {
                res[index++] = nums[deque.getFirst()];
            }
        }

        return res;
    }
}

