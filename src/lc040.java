import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * 题目: 在给定的含重复数字的数组中, 找出所有数字和为target的组合, 数组中的每个数字只能使用一次
 *       (所有数字都是正整数, 解集不能包含重复的组合)
 *
 * 难度: medium
 * 
 * 思路: 回溯
 */
class Solution {
    /**
     * 时间复杂度: O(C _n ^n)
     * 空间复杂度: O(n) (n为递归栈的深度)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 排序数组, 为了后面方便跳过相同数字
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, target, 0, 0, new ArrayList(), ans);

        return ans;
    }

    private void dfs(int[] candidates, int target, int index, int curSum, List<Integer> sublist, List<List<Integer>> ans) {
        if (curSum == target) {
            ans.add(new ArrayList(sublist));
            return;
        }
        if (curSum > target) return;
        // 使用index, 得到集合的数字会按candidates中的顺序排列, 若重复使用当前数字, 则它们会放在一起
        // 如1, 2 -> 4 使用index只会出现1, 1, 2
        //         而不使用index可能出现1, 2, 1, 则解合中包含重复的组合
        // index相当于背包问题的当前index位置数字放与不放
        for (int i = index; i < candidates.length; i++) {
            // 与lc039的不同之处:
            // 数组元素可能含有相同的元素, 进行组合时就有可能出现重复的组合, 处理方法如下(lc047为另一种方法)
            // 跳过重复组合(当前位置元素相同时则跳过)
            if (i != index && candidates[i] == candidates[i - 1]) continue;
            sublist.add(candidates[i]);
            // index == i, 不重复使用数组中的元素(与lc039的不同之处)
            dfs(candidates, target, i + 1, curSum + candidates[i], sublist, ans);
            sublist.remove(sublist.size() - 1); // 回溯
        }
    }
}