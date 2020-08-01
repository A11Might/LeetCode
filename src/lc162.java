/**
 * @author qhhu
 * @date 2020/7/29 - 9:06
 *
 * 题目：在给定数组中找到峰值元素并返回索引，数组中的元素 nums[i] != nums[i + 1]。
 *
 * 难度：medium
 *
 * 思路：当 nums[i - 1] < nums[i] 时，如果 nums[i - 1], nums[i], ... nums[n - 1] 是单调的，则 nums[n - 1] 就是峰值；
 *                                  如果 nums[i - 1], nums[i], ... nums[n - 1] 不是单调的，则 [i, n - 1] 中一定包含一个峰值。
 *      当 nums[i - 1] > nums[i] 时，同理。
 *      使用二分搜索，通过上述判断，每次将检索区间缩小一半。
 */
class Solution {
    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid - 1] < nums[mid]) l = mid;
            else r = mid - 1;
        }
        return l;
    }
}