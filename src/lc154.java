/**
 * @author qhhu
 * @date 2020/2/16 - 16:33
 *
 * [154] 寻找旋转排序数组中的最小值 II
 *
 * 题目: 将给定升序数组在某个点上进行旋转, 返回该数组中的最小元素
 *      (数组中存在重复元素)
 *
 * 难度: hard
 *
 * 思路: 二分查找, 旋转排序数组可以拆分为两个排序数组 nums1 和 nums2, 并且 nums1任意元素 >= nums2 任意元素,
 *               使用二分查找这两个数组的分界点(nums2 的首元素)即最小元素.
 */
class Solution {
    /**
     * 时间复杂度: O(logn) (在特例情况下会退化到 O(n) (如[1, 1, 1, 1]))
     * 空间复杂度: O(1)
     */
    public int findMin(int[] nums) {
        /**
         和 I 的做法类似, 都是二分法, 每次进入无序的那部分找出最小值
         但是由于有重复值的情况, 需要加入 mid 元素等于 hi 元素的情况
         此时应该将 hi 减 1 防止重复数字是最小元素
         **/
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 当前mid的值大于当前区间的hi值, mid肯定在左有序区间内, 最小元素在mid右边
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                // 当前mid的值小于当前区间的hi值, mid肯定在右有序区间内, mid可能为最小元素
                hi = mid;
            } else {
                // 当前mid值等于当前区间的hi值, 无法判断mid在哪个有序区间, 例如: [1,0,1,1,1], [1,1,1,0,1],
                // 这时采用 hi = hi  - 1 来解决此问题.
                // 证明:
                // 此操作不会使数组越界: 因为迭代条件保证了 hi > lo >= 0;
                // 此操作不会使最小值丢失: 假设 nums[hi] 是最小值, 有两种情况:
                // 若 nums[hi] 是唯一最小值: 那就不可能满足判断条件 nums[mid] == nums[hi],
                // 因为 mid < hi (lo != hi 且 mid = (lo + hi) // 2 向下取整);
                // 若 nums[hi] 不是唯一最小值, 由于 mid < right 而 nums[mid] == nums[right],
                // 即还有最小值存在于 [left, right - 1] 区间, 因此不会丢失最小值
                hi--;
            }
        }
        return nums[lo];
    }
}
