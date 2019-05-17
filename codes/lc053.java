/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 * 
 * 对于含有正数的序列而言，最大子序列肯定是正数，所以头尾肯定都是正数
 * 从第一个正数开始算起，每往后加一个数便更新一次和的最大值
 * 当当前和成为负数时，则表明此前序列无法为后面提供最大子序列和，重新确定序列首项.
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0], sum = 0; // res为最大子序和，sum为当前子序和
        for (int i = 0; i < nums.length; ++i) {
            // 若sum小于0时，当前子序和对最大子序和无作用，重新计算子序和
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            // 时刻更新最大子序和
            res = Math.max(res, sum);
        }
        return res;
    }
}

