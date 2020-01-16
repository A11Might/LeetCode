/*
 * @lc app=leetcode.cn id=167 lang=java
 *
 * [167] 两数之和 II - 输入有序数组
 * 
 * 题目: 在给定的升序排列的数组中，找到两个和为目标数的数
 *      (索引从1开始)
 *
 * 难度: easy
 * 
 * 思路: 使用指针碰撞，将两指针初始置于第一个元素和最后一个元素位置，比较这两个元素之和与目标值的大小,
 *       利用数组有序的特性来移动指针, 以求找到符合题意的答案
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) return null;
        int len = numbers.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            // 如果比目标值大，我们将较大指针减小一
            if (sum > target) {
                right--;
            // 如果比目标值小，我们将较小元素指针增加一
            } else if (sum < target) {
                left++;
            } else {
                return new int[] {left + 1, right + 1};
            }
        }

        throw new IllegalArgumentException("No solution");
    }
}

