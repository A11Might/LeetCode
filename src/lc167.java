/*
 * @lc app=leetcode.cn id=167 lang=java
 *
 * [167] 两数之和 II - 输入有序数组
 *
 * 题目：在给定的升序排列的数组中，找到两个和为目标数的数
 *      （索引从 1 开始）
 *
 * 难度：easy
 *
 * 思路：使用指针碰撞，将两指针初始置于第一个元素和最后一个元素位置，比较这两个元素之和与目标值的大小,
 *      利用数组有序的特性来移动指针, 以求找到符合题意的答案
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            if (nums[l] + nums[r] > target) r--; // 如果比目标值大，我们将较大指针减小一
            else if (nums[l] + nums[r] < target) l++; // 如果比目标值小，我们将较小元素指针增加一
            else return new int[]{l + 1, r + 1};
        }
        return new int[0];
    }
}

