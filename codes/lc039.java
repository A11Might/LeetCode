import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 *
 * 题目: 在给定的无重复元素的数组中, 找出所有数字和为target的组合, 数组中的每个数字可以多次使用
 *       (所有数字都是正整数, 解集不能包含重复的组合)
 *
 * 难度: medium
 * 
 * 思路: 回溯
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, 0, ans, new ArrayList<>(), 0, target);

        return ans;
    }

    private void dfs(int[] candidates, int index, List<List<Integer>> ans, List<Integer> sublist, int curSum, int target) {
        if (curSum == target) {
            ans.add(new ArrayList<>(sublist));
        }
        if (curSum > target) {
            return;
        }
        // 使用index, 得到集合的数字会按candidates中的顺序排列, 若重复使用当前数字, 则它们会放在一起
        // 如1, 2 -> 4 使用index只会出现1, 1, 2
        //         而不使用index可能出现1, 2, 1, 则解合中包含重复的组合
        // index相当于背包问题的当前index位置数字放与不放但还有放多个(即index不变)
        for (int i = index; i < candidates.length; i++) {
            sublist.add(candidates[i]);
            dfs(candidates, i, ans, sublist, curSum + candidates[i], target); // index == i，数组中的元素可以被重复选取
            sublist.remove(sublist.size() - 1); // 还原sublist，防止对下一步递归产生影响
        }
    }
}