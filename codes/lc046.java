import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * 题目：返回给定没有重复数字序列的所有可能全排列
 *
 * 难度：medium
 *
 * 思路：回溯
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, ans);

        return ans;
    }

    private void dfs(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(getList(nums));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i); // 为第index位选择一个字符
            dfs(nums, index + 1, ans); // 全排列剩下的字符
            swap(nums, index, i); // 还原数组
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

