/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 * 
 * 题目: 将给定数组中所有的0移动至数组末尾, 同时保持其他非0元素的相对位置.
 *      (必须在原数组上操作, 不能拷贝额外的数字; 尽量减少操作次数)
 *
 * 难度: easy
 *
 * 思路: 1. 暴力, 遇到0则将后面元素全部前移一位
 *       2. 类partition, 将数组分为非0部分和0部分, 遍历数组, 当遇到非0元素则将其移至非0部分的末尾(题意说尽量减少操作次数
 *          所以直接使用非0元素覆盖0元素即可, 不用交换), 可以保持非0元素的相对位置, 遍历完数组后将0部分的元素全部置为0.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int notZero = -1; // 非0区间(-无穷, k]
        for (int i = 0; i < len; i++) {
            // 当前元素为非0元素需要放置在非0区间
            // k + 1为非0区间后第一个位置, i为当前非0元素位置, 直接覆盖即可
            if (nums[i] != 0) {
                nums[++notZero] = nums[i];
            }
        }

        // 将0部分的元素全部置为0
        while (notZero + 1 < len) {
            nums[++notZero] = 0;
        }
    }
}