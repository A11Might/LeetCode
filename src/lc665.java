/**
 * @author qhhu
 * @date 2020/1/24 - 22:08
 *
 * [665] 非递减数列
 *
 * 题目: 判断一个数组是否能只修改一个数就成为非递减数组
 *      (非递减数列, 对于数组中所有的i(1 <= i < n), 满足array[i] <= array[i + 1]
 *
 * 难度: easy
 *
 * 思路: 在出现nums[i] < nums[i - 1]时, 需要考虑的是应该修改数组的哪个数, 使得本次修改能使i之前的数组成为非递减数组,
 *      并且不影响后续的操作. 优先考虑令nums[i - 1] = nums[i], 因为如果修改nums[i] = nums[i - 1]的话, 那么
 *      nums[i]这个数会变大, 就有可能比nums[i + 1]大, 从而影响了后续操作. 还有一个比较特别的情况就是nums[i] < nums[i - 2],
 *      修改nums[i - 1] = nums[i]不能使数组成为非递减数组, 只能修改 nums[i] = nums[i - 1]
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) continue;
            count++;
            if (i >= 2 && nums[i - 2] > nums[i]) {
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }

        return count <= 1;
    }
}
