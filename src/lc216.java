import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/22 - 11:54
 *
 * [216] 组合总数 III
 *
 * 题目: 在1-9的正整数中, 找到相加之和为n的k个数的组合, 每种组合中不存在重复的数字
 *       (所有数字都是正整数, 解集不能包含重复的组合)
 *
 * 难度: medium
 *
 * 思路: 回溯
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(k, n, 1, 0, new ArrayList(), ans);

        return ans;
    }

    private void dfs(int k, int n, int index, int curSum, List<Integer> sublist, List<List<Integer>> ans) {
        // 统一处理边界条件
        if (sublist.size() == k || curSum >= n) {
            if (sublist.size() == k && curSum == n) {
                ans.add(new ArrayList(sublist));
            }
            return;
        }
        // 使用index, 得到集合的数字会按1-9中的顺序排列
        // 如1, 2 -> 3 使用index只会出现1, 2
        //         而不使用index可能出现2, 1 则解合中包含重复的组合
        // index相当于背包问题的当前index位置数字放与不放
        for (int i = index; i <= 9; i++) {
            sublist.add(i);
            // index = i + 1, 不重复使用数组中的元素
            dfs(k, n, i + 1, curSum + i, sublist, ans);
            sublist.remove(sublist.size() - 1); // 回溯
        }
    }
}