/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 * 
 * 使用二分查找，找到旋转的下标 rotation_index ，也就是数组中最小的元素
 * 在选中的数组区域中再次使用二分查找，寻找目标元素
 * 
 * tips：使用二分查找要考虑大于等于小于三种情况
 */
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int rotationIndex = findRotationIndex(nums, 0, n - 1);
        if (rotationIndex == 0) {
            return binarySearch(nums, 0, n - 1, target);
        }
        if (nums[rotationIndex] == target) {
            return rotationIndex;
        } else if (nums[0] <= target) { // <-----
            return binarySearch(nums, 0, rotationIndex - 1, target);
        } else {
            return binarySearch(nums, rotationIndex, n - 1, target);
        }
    }

    // 旋转排序数组后，数组分为左升序区间和右升序区间，并且左升序区间所有元素都大于右升序区间
    private int findRotationIndex(int[] nums, int left, int right) {
        // nums数组整个升序排列，旋转的下标为0
        if (nums[left] < nums[right]) {
            return 0;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 找到了左升序区间和右升序区间的交界处，即旋转的下标
            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;
                // 中点位置在右升序区间内
            } else if (nums[mid] < nums[0]) { // 需要考虑等于情况，等于时中点位置在左升序区间内 <-----
                right = mid - 1;
                // 中点位置在左升序区间内
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

