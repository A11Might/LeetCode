import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 * 
 * 题意: 返回一个不含重复元素的整数数组所有可能的子集(幂集)
 *      (解集不能包含重复的子集)
 *
 * 难度: medium
 * 
 * 思路:  1. 回溯
 *       2. 遇到一个数就把所有子集加上该数组成新的子集, 遍历完毕即是所有子集
 */
class Solution {
    /**
     * 时间复杂度: O(∑ _k=1 ^n C _n ^k)
     * 空间复杂度: O(n) (递归栈的深度)
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, new ArrayList(), ans);

        return ans;
    }

    private void dfs(int[] nums, int index, List<Integer> sublist, List<List<Integer>> ans) {
        // 求所有子集, 所以每当收集到一个集合就是立刻记录下
        ans.add(new ArrayList(sublist));
        if (index == nums.length) return;

        // 使用index, 得到集合的数字会按nums中的顺序排列
        // 如1, 2   使用index只会出现1, 2
        //         而不使用index可能出现2, 1, 则解合中包含重复的组合
        // index相当于背包问题的当前index位置数字放与不放
        for (int i = index; i < nums.length; i++) {
            sublist.add(nums[i]);
            // index = i + 1(求的是子集, 原数组中的元素不能重复使用)
            dfs(nums, i + 1, sublist, ans);
            sublist.remove(sublist.size() - 1); // 回溯
        }
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // 先加入空集
        // 遇到一个数就把所有子集加上该数组成新的子集
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; ++i) {
            int size = res.size();
            for (int j = 0; j < size; ++j) {
                List<Integer> subset = new ArrayList<>(res.get(j));
                subset.add(nums[i]);
                res.add(subset);
            }
        }

        return res;
    }
}

