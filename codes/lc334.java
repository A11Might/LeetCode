/*
 * @lc app=leetcode.cn id=334 lang=java
 *
 * [334] 递增的三元子序列
 * 
 * min始终记录最小元素，mid为某个子序列里第二大的数
 * 接下来不断更新min，同时保持mid尽可能的小
 * 如果下一个元素比mid大，说明找到了三元组
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) { // num == min时，min不变，保持三元子序列顺序
                min = num;
            } else if (num <= mid) {
                mid = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
