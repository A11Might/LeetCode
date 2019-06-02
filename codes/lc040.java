import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 * 
 * 回溯算法
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int target, int index) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(tempList));
        } else {
            for (int i = index; i < nums.length; ++i) {
                // 跳过重复组合(当前位置元素相同时则跳过(第一个相同元素的组合是最多的，其他跳过))
                if (i > index && nums[i] == nums[i - 1]) {
                    continue;
                }
                tempList.add(nums[i]);
                backtrack(res, tempList, nums, target - nums[i], i + 1); // 每个元素只用一次
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

