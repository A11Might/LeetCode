/*
 * @lc app=leetcode.cn id=31 lang=java
 *
 * [31] 下一个排列
 * 
 * 1、暴力：找出所有排列，选出目标排列(太暴力了，timeout)
 * 2、从后往前遍历，找到第一个降序元素，将其与其后第一个大于它大元素交换，再将其后的元素排列升序 即为目标答案
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (nums[i] >= nums[i + 1]) {
            i--;
        }
        // 当i < 0时，当前元素为最大排列，直接反转为最小排列
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    // 将元素排列升序，已知当前排列是降序，前后位置元素交换即为降序
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }
}

