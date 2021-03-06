import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=448 lang=java
 *
 * [448] 找到所有数组中消失的数字
 *
 * 题目: 给定一个范围在1 <= a[i] <= n(n为数组大小)的整型数组, 数组中的元素一些出现了两次, 另一些只出现一次.
 *      找到所有在[1, n]范围之间未出现在数组中的数字(要求不使用额外空间且时间复杂度O(n))
 *
 * 难度: easy
 * 
 * 思路: 同[442]数组中重复的数据, 将数组中的元素放在对应的下标上, 然后在遍历一遍数组来寻找在[1, n]范围之间未出现在数组中的数字.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 当前元素不在对应下标上并且对应下标上也没有对应元素
            // (若出现重复元素, 当前元素不在正确位置, 但正确位置上有正确元素)
            while (i != nums[i] - 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 寻找在[1, n]范围之间未出现在数组中的数字
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i != nums[i] - 1) {
                // 唯一与[442]不同的地方
                ret.add(i + 1);
            }
        }
        return ret;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}