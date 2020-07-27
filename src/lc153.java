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
 * 思路: 二分查找, 该旋转点左边的数满足 nums[i] >= nums[0]；而该旋转点右边的数不满足这个条件，所以分界点就是整个数组的最小值。
 *      另外，不要忘记处理数组完全单调的特殊情况。
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 数组完全单调的情况
        if (nums[l] < nums[r]) return nums[l];
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < nums[0]) r = mid;
            else l = mid + 1;
        }
        return nums[l];
    }
}