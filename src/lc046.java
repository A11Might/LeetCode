import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * 题目: 返回给定没有重复数字序列的所有可能全排列
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
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        int len = nums.length;
        boolean[] visited = new boolean[len];
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, visited, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(int[] nums, boolean[] visited, int cnt, List<Integer> subList, List<List<Integer>> ans) {
        if (cnt == nums.length) {
            ans.add(new ArrayList(subList)); // 重新构造一个 List
            return;
        }
        // 每次从给定序列中选取一个不同的数字放在当前位置
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            subList.add(nums[i]);
            dfs(nums, visited, cnt + 1, subList, ans);
            visited[i] = false;
            subList.remove(subList.size() - 1);
        }
    }

    /**
     * 时间复杂度: O(A _n ^n)
     * 空间复杂度: O(n) (递归栈的深度为n)
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, ans);

        return ans;
    }

    // 先在给定序列中选择一个数字放在当前位置, 再对剩余的数字进行全排列
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

