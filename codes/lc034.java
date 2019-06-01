/*
 * @lc app=leetcode.cn id=34 lang=java
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 * 
 * 语义约定：二分查找返回不大于目标元素的最后一个元素
 *          1> 当有多个命中元素时，必须返回最靠后的元素
 *          2> 失败时，应返回小于目标元素的最大者(含哨兵(lo - 1))
 * 二分查找目标元素 - 1的下标加一和目标元素的下标
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = binarySearch(nums, target);
        // 当查找失败时，返回的是左侧哨兵节点 -1，或右侧哨兵节点的左临 n - 1
        if (start == -1 || nums[start] != target) {
            return new int[] {-1, -1};
        }
        // 二分查找目标元素 - 1的下标加一和目标元素的下标即为在排序数组中查找元素的第一个和最后一个位置
        return new int[] {binarySearch(nums, target - 1) + 1, start};
    }

    // 二分查找返回不大于目标元素的最后一个元素
    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
}

