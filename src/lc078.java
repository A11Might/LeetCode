import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 * 
 * 题意：返回一个不含重复元素的整数数组所有可能的子集
 *
 * 难度: medium
 * 
 * 思路：1、回溯(类似背包问题放与不放)
 *       2、遇到一个数就把所有子集加上该数组成新的子集，遍历完毕即是所有子集
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(int[] nums, int index, List<Integer> sublist, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(sublist));
            return;
        }
        // 加入当前元素
        sublist.add(nums[index]);
        dfs(nums, index + 1, sublist, ans);
        // 不加入当前元素
        sublist.remove(sublist.size() - 1);
        dfs(nums, index + 1, sublist, ans);
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

