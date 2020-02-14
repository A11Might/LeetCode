import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/2/13 - 10:22
 *
 * [442] 数组中重复的数据
 *
 * 题目: 给定一个整数数组a, 其中1 <= a[i] <= n(n为数组长度), 其中有些元素出现两次而其他元素出现一次, 返回所有值出现两次的元素.
 *      (要求不使用额外空间且时间复杂度O(n))
 *
 * 难度: medium
 *
 * 思路: 同[448]找到所有数组中消失的数字, 通过交换数组元素, 使得数组上的所有元素都在正确的位置上, 这样重复元素就在不正确的位置上.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 当前元素不在对应下标上并且对应下标上也没有对应元素
            // (若出现重复元素, 当前元素不在正确位置, 但正确位置上有正确元素)
            while (i != nums[i] - 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 寻找出现两次的元素
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i != nums[i] - 1) {
                // 唯一与[448]不同的地方
                ret.add(nums[i]);
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
