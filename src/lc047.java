import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * 题目: 返回给定包含重复数字序列的所有不重复的全排列
 *
 * 难度: medium
 * 
 * 思路: 1. 回溯, 每次从给定序列中选取一个不同的数字放在当前位置
 *      2. 减而治之, 先在给定序列中选择一个数字放在当前位置, 再对剩余的数字进行全排列
 *
 */
class Solution {
    /**
     * 时间复杂度: O(A _n ^n)
     * 空间复杂度: O(n) (递归栈的深度为n)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) Collections.emptyList();
        int len = nums.length;
        boolean[] visited = new boolean[len];
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, visited, new ArrayList(), ans);

        return ans;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> sublist, List<List<Integer>> ans) {
        if (sublist.size() == nums.length) {
            ans.add(new ArrayList(sublist));
            return;
        }
        // 数组元素可能含有相同的元素, 进行排列时就有可能出现重复的排列, 处理方法如下(lc040为另一种方法)
        // 每个index上有一个HashSet, 判断该位置是否出现过某个字符
        HashSet<Integer> dict = new HashSet<>();
        // 每次从给定序列中选取一个不同的数字放在当前位置
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || dict.contains(nums[i])) continue;
            visited[i] = true;
            dict.add(nums[i]);
            sublist.add(nums[i]);
            dfs(nums, visited, sublist, ans);
            sublist.remove(sublist.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 时间复杂度: O(A _n ^n)
     * 空间复杂度: O(n) (递归栈的深度为n)
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, ans);

        return ans;
    }

    private void dfs(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(getList(nums));
            return;
        }
        // 数组元素可能含有相同的元素, 进行排列时就有可能出现重复的排列, 处理方法如下:
        // 每个index上有一个HashSet, 判断该位置是否出现过某个字符
        HashSet<Integer> dict = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (dict.contains(nums[i])) continue;
            dict.add(nums[i]);
            swap(nums, index, i); // 为第index位选择一个字符
            dfs(nums, index + 1, ans); // 全排列剩下的字符
            swap(nums, index, i); // 还原数组
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }

    private List<Integer> getList(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            ans.add(num);
        }

        return ans;
    }
}