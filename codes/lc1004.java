/*
 * @lc app=leetcode.cn id=1004 lang=java
 *
 * [1004] 最大连续1的个数 III
 * 
 * 题意：给定一个由若干0和1组成的数组A，我们最多可以将K个值从0变成1返回仅包含1的最长(连续)子数组的长度
 * 
 * https://www.bilibili.com/video/av55028357
 * 思路：使用双指针固定一个滑动窗口，在A上滑动，保持窗口中最多只有K个0，返回窗口可能的最大宽度
 */
class Solution {
    public int longestOnes(int[] A, int K) {
        int lo = 0, hi = 0; // 滑动窗口的左边界和右边界
        int preSum = 0; // 窗口中0的个数
        int n = A.length;

        int res = 0;
        for (;hi < n; ++hi) {
            preSum += (A[hi] == 1 ? 0 : 1); // 统计窗口中0的个数
            // 当窗口中0的个数超过限制，移除窗口中的第一个0
            if (preSum > K) {
                while (lo < hi && A[lo] == 1) {
                    lo++;
                }
                lo++; // 当前lo位置为0，跳过
                preSum--; // 窗口中0的个数也减去跳过的这一个
            }
            // 实时更新最长(连续)子数组的长度
            if (hi - lo + 1 > res) {
                res = hi - lo + 1;
            }
        }

        return res;
    }
}

