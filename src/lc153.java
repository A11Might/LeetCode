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
 * 思路: 二分查找, 旋转排序数组可以拆分为两个排序数组 nums1 和 nums2, 并且 nums1任意元素 >= nums2 任意元素,
 *               使用二分查找这两个数组的分界点(nums2 的首元素)即最小元素.
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 当前mid的值大于当前区间的hi值, mid在左有序区间内, 最小元素在mid右边
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                // 当前mid的值小于当前区间的hi值, mid在右有序区间内, mid可能为最小元素
                hi = mid;
            }
        }
        return nums[lo];
    }
}
