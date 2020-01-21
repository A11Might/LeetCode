/**
 * @author qhhu
 * @date 2020/1/21 - 11:15
 *
 * [540] 有序数组中的单一元素
 *
 * 题目: 给定一个只包含整数的有序数组, 除唯一一个数之后出现两次, 其它每个元素都会出现两次, 返回这个数
 *
 * 难度: medium
 *
 * 思路: 对偶数位置进行二分搜索, 令index为Single Element在数组中的位置. 在index之后, 数组中原来存在的成对状态被改变.
 *      如果m为偶数, 并且m + 1 < index, 那么nums[m] == nums[m + 1]; m + 1 >= index, 那么nums[m] != nums[m + 1]
 *
 *      即使数组没有经过排序, 只要将同一元素放在一起, 该算法仍然起作用(例: [10, 10, 4, 4, 7, 11, 11, 12, 12, 2, 2]).
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        int lo = 0, hi = len - 1; // 区间[lo, hi]
        // lo == hi, 则当前搜索空间为1个元素, 那么该元素为单个元素
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (mid % 2 != 0) {
                // lo, hi都为偶数, 区间中的元素个数为奇数
                // 当mid为奇数时, mid++, 可能会使mid == hi, 后续的nums[mid + 1]会越区间的界
                // 而mid--最多会使mid == lo, 后续的nums[mid + 1]不会越区间的界
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                // 单个元素在mid + 1之后
                lo = mid + 2;
            } else {
                // 单个元素为mid或mid之前的元素
                hi = mid;
            }
        }

        return nums[lo];
    }
}