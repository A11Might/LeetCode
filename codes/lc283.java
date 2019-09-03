/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 * 
 * 1、暴力，遇到0则将后面元素全部前移一位
 * 2、类partition，将数组分为非0部分和0部分
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int k = -1; // 非0区间(-无穷, k]
        for (int i = 0; i < nums.length; i++) {
            // 当前元素为非0元素需要放置在非0区间
            // k + 1为非0区间后第一个位置，i为当前非0元素位置，交换元素即可
            if (nums[i] != 0) {
                // 若k + 1 == i，则不需要交换位置
                if (++k != i) { 
                    swap(nums, k, i);
                }
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

