import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * 题目: 在给定的含重复数字的数组中,  找出所有数字和为target的组合, 数组中的每个数字只能使用一次
 *       (所有数字都是正整数, 解集不能包含重复的组合)
 *
 * 难度: medium
 * 
 * 思路: 回溯
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates); // 排序数组, 为了后面方便跳过相同数字
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
        // index相当于背包问题的当前index位置数字放与不放
        for (int i = index; i < candidates.length; i++) {
            // 跳过重复组合(当前位置元素相同时则跳过)
            if (i != index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sublist.add(candidates[i]);
            dfs(candidates, i + 1, ans, sublist, curSum + candidates[i], target); // index = i + 1, 每个元素只用一次
            sublist.remove(sublist.size() - 1);
        }
    }
}