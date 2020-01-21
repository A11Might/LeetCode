/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 *
 * 题目: 计算并返回x的平方根, 其中x为非负整数, 返回结果只保留整数部分, 小数部分将被舍去
 *
 * 难度: easy
 * 
 * 思路: 在[1, x)中二分查找, 返回平方不大于x最大的数
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int mySqrt(int x) {
        // 1是特例, 1的平方根是1; 其他数的的平方根都小于其自身, 所以将右边界设置为x
        if (x == 1) return 1;
        int lo = 1, hi = x; // 区间[1, x)
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            double pow = Math.pow(mid, 2);
            // 将数组[1, x)分为 <= 和 > 两个部分, 返回<=部分的的最大值(lo - 1)
            // 即为平方不大于x最大的数(即只保留整数的部分，小数部分被舍去的结果)
            if (pow <= x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }
}
