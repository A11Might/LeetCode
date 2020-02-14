/**
 * @author qhhu
 * @date 2020/2/13 - 10:22
 *
 * [645] 错误的集合
 *
 * 题目: 给定一个数组nums, 包含从1到n的整数, 由于发生错误, 数组中的一个元素变成了另一个元素, 返回重复的数字和丢失的数字.
 *      (给定的数组时无序的)
 *
 * 难度: easy
 *
 * 思路: 1. 对数组排序
 *      2. 通过交换数组元素, 使得数组上的所有元素都在正确的位置上, 这样重复元素就在不正确的位置上, 该位置上应有的数字的即为丢失的数字.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 当前元素不在正确的位置上, 并且正确位置上也无正确元素
            // (若出现重复元素, 当前元素不在正确位置, 但正确位置上有正确元素)
            while (i != nums[i] - 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        // 寻找重复元素和丢失数字
        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                return new int[] {nums[i], i + 1};
            }
        }

        throw new IllegalArgumentException("no solution");
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}
