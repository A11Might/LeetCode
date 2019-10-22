import java.util.Stack;

/*
 * @lc app=leetcode.cn id=581 lang=java
 *
 * [581] 最短无序连续子数组
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/
 * 
 * 1、直接寻找第一个和最后一个不是升序的元素
 * 2、排序目标数组，找出发生变化的子数组第一个和最后一个元素
 */
class Solution {
    public int findUnsortedSubarray1(int[] nums) {
        int l = nums.length, r = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }


    // 啥玩意
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    // 啥玩意
    public int findUnsortedSubarray(int[] nums) { // [1,2,4,5,3]
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    public int findUnsortedSubarray2(int[] nums) {
        int[] copyOfNums = nums.clone();
        java.util.Arrays.sort(copyOfNums);
        int start = nums.length, end = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (copyOfNums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end - start >= 0 ? end - start + 1 : 0;
    }

}

