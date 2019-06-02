import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 * 
 * 回溯算法
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int target, int index) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(tempList)); // ???
        } else {
            // index相当于背包问题的放与不放但还有放多个(即index不变)
            for (int i = index; i < nums.length; ++i) {
                tempList.add(nums[i]);
                backtrack(res, tempList, nums, target - nums[i], i); // index == i，数组中的元素可以被重复选取
                tempList.remove(tempList.size() - 1); // 还原tempList，防止对下一步递归产生影响
            }
        }
    }
}
