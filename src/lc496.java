import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=496 lang=java
 *
 * [496] 下一个更大元素 I
 * 
 * 题意：两个没有重复元素的数组nums1和nums2，其中nums1是nums2的子集，找到 nums1 中每个元素在nums2中的下一个比其大的值，不存在返回-1
 * 
 * https://www.bilibili.com/video/av54485528
 * 思路：单调栈
 */
class Solution {
    // 从后往前遍历nums2
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[n];

        for (int i = m - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : nums2[stack.peek()]);
            stack.push(i);
        }

        for (int i = 0; i < n; ++i) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }

    // 从左往右遍历nums2
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }

            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}

