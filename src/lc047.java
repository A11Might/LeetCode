import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * 题目：返回给定包含重复数字序列的不重复的全排列
 *
 * 难度：medium
 * 
 * 思路：回溯
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, ans);

        return ans;
    }

    private void dfs(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(getList(nums));
        }
        HashSet<Integer> set = new HashSet<>(); // 每个index上有一个HashSet，判断该位置是否出现过某个字符
        for (int i = index; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                swap(nums, index, i); // 为第index位选择一个字符
                dfs(nums, index + 1, ans); // 全排列剩下的字符
                swap(nums, index, i); // 还原数组
            }
            set.add(nums[i]);
        }
    }

    private List<Integer> getList(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int num : arr) {
            res.add(num);
        }

        return res;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}
