/*
 * @lc app=leetcode.cn id=167 lang=java
 *
 * [167] 两数之和 II - 输入有序数组
 * 
 * 题目：在给定的升序排列的数组中，找到两个和为目标数的数
 *      (索引从1开始)
 * 
 * 思路：指针碰撞，
 *       numbers[i] + numbers[j] > target时，j减小以减小两数之和
 *       numbers[i] + numbers[j] < target时，i增加以增加两数之和
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[] {};
        }
        for (int i = 0, j = numbers.length - 1; i < j; ) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[] {i + 1, j + 1};
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        
        return new int[] {-1, -1};
    }
}

