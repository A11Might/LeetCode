/*
 * @lc app=leetcode.cn id=278 lang=java
 *
 * [278] 第一个错误的版本
 *
 * 题目: 在n个版本[1, 2, ..., n]中, 找出导致之后所有版本出错的第一个错误的版本
 *
 * 难度: easy
 * 
 * 思路: 二分查找, 返回第一个为true的元素
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int firstBadVersion(int n) {
        // 不用管n取到取不到, 最后的hi可以返回n(我的锅, 老是想不能去到想+1, 然后就越界了)
        int lo = 1, hi = n; // 区间[lo, hi)
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 将区间分为不是错误版本和错误版本两个区间
            if (!isBadVersion(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}
