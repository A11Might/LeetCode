/*
 * @lc app=leetcode.cn id=34 lang=java
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 *
 * 题目: 在给定升序的整数数组nums中, 返回给定目标值target在数组中的开始位置和结束位置
 *
 * 难度: medium
 * 
 * 思路: 语义约定, 二分查找返回不大于目标元素的最后一个元素
 *          1. 当有多个命中元素时，必须返回最靠后的元素
 *          2. 失败时, 应返回小于目标元素的最大者(含哨兵(lo - 1))
 *      二分查找目标元素 - 1的下标加一和目标元素的下标
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = binarySearch(nums, target);
        // 本方法的二分查找返回不大于目标元素的最后一个元素
        // 当查找失败时, 返回的是左侧哨兵节点 -1 或右侧哨兵节点的左临 n - 1(判断其是否为目标元素, 即可判断nums中有无目标元素)
        // 若二分查找返回大于等于目标元素的第一个元素时
        // 查找失败时返回右侧哨兵 n 或者左侧哨兵的右临0(判断其是否为目标元素, 即可判断nums中有无目标元素)
        if (start == -1 || nums[start] != target) return new int[] {-1, -1};
        // 二分查找目标元素 - 1的下标加一和目标元素的下标即为在排序数组中查找元素的第一个和最后一个位置
        return new int[] {binarySearch(nums, target - 1) + 1, start};
    }

    // 二分查找返回不大于目标元素的最后一个元素
    private int binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (target < nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo - 1;
    }

    /**
     * 同上
     */
    public int[] searchRange2(int[] nums, int target) {
        int start = binarySearch2(nums, target);
        if (start == nums.length || nums[start] != target) return new int[] {-1, -1};
        int end = binarySearch2(nums, target + 1) - 1;
        return new int[] {start, end};

    }

    private int binarySearch2(int[] nums, int target) {
        int len = nums.length;
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}

