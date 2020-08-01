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
 * 思路: 二分查找, 右边的数的最后一段可能等于 nums[0]，先将这段删除，这样其余部分就满足二分性质：
 *               该旋转点左边的数满足 nums[i] >= nums[0]；而该旋转点右边的数不满足这个条件，所以分界点就是整个数组的最小值。
 *               另外，不要忘记处理数组完全单调的特殊情况。
 */
class Solution {
    /**
     * 时间复杂度: O(logn) (在特例情况下会退化到 O(n) (如 [1, 1, 1, 1]))
     * 空间复杂度: O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 将最后等于 nums[0] 的水平的一段删除掉
        while (l < r && nums[r] == nums[l]) r--;
        // 数组完全单调的情况
        if (nums[l] <= nums[r]) return nums[l];
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= nums[0]) l = mid + 1;
            else r = mid;
        }
        return nums[l];
    }
}