/**
 * @author qhhu
 * @date 2020/1/21 - 14:21
 *
 * [153] 寻找旋转排序数组中的最小值
 *
 * 题目: 将给定升序数组在某个点上进行旋转, 返回该数组中的最小元素
 *      (数组中不存在重复元素)
 *
 * 难度: medium
 *
 * 思路: 二分查找, 将区间[0, len)分为大于nums[len - 1]和小于等于nums[len - 1]两个部分, 返回小于等于部分的第一个元素
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int findMin(int[] nums) {
        int len = nums.length;
        int lo = 0, hi = len; // 区间[lo, hi)
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 将区间[0, len)分为大于nums[len - 1]和小于等于nums[len - 1]两个部分
            // 即将原区间分为两个升序的子区间, 第二个子区间的第一个元素为原数组中的最小元素
            if (nums[mid] <= nums[len - 1]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return nums[lo];
    }
}
