/*
 * @lc app=leetcode.cn id=448 lang=java
 *
 * [448] 找到所有数组中消失的数字
 * 
 * 将数组中的元素放在对应的下标上，然后在遍历一遍数组
 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] - 1 != i // 当前元素不在对应下标上
                && nums[nums[i] - 1] - 1 != nums[i] - 1) { // 并且对应下标上也没有对应元素
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] - 1 != i) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

