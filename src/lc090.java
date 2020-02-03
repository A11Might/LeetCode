import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/23 - 23:39
 *
 * [90] 子集 II
 *
 * 题目: 给定一个可能包含重复元素的整数数组 nums, 返回该数组所有可能的子集(幂集)
 *      (解集不能包含重复的子集)
 *
 * 难度: medium
 *
 * 思路: 回溯
 */
class Solution {
    /**
     * 时间复杂度: O(∑ _k=1 ^n C _n ^k)
     * 空间复杂度: O(n) (递归栈的深度)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        Arrays.sort(nums); // 排序数组, 方便后面跳过相同的数字
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, new ArrayList(), ans);

        return ans;
    }

    private void dfs(int[] nums, int index, List<Integer> sublist, List<List<Integer>> ans) {
        // 求所有子集, 所以每当收集到一个集合就是立刻记录下
        ans.add(new ArrayList(sublist));
        if (index == nums.length) return;
        // 使用index, 得到集合的数字会按大小顺序排列
        // 如1, 2 -> 3 使用index只会出现1, 2
        //         而不使用index可能出现2, 1 则解合中包含重复的组合
        // index相当于背包问题的当前index位置数字放与不放
        for (int i = index; i < nums.length; i++) {
            // 当前位置数字重复, 跳过
            if (i != index && nums[i] == nums[i - 1]) continue;
            sublist.add(nums[i]);
            // 求子集, 所以每个元素只能用一次, index = i + 1
            dfs(nums, i + 1, sublist, ans);
            sublist.remove(sublist.size() - 1); // 回溯
        }
    }
}