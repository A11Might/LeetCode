import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/18 - 23:35
 *
 * [77] 组合
 *
 * 题目: 给定两个整数 n 和 k, 返回 1 ... n 中所有可能的 k 个数的组合
 *
 * 难度: medium
 *
 * 思路: 回溯
 */
class Solution {
    /**
     * 时间复杂度: O(C _n ^k)
     * 空间复杂度: O(k) (k为递归栈的深度)
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(n, k, 1, new ArrayList(), ans);

        return ans;
    }

    private void dfs(int n, int k, int index, List<Integer> sublist, List<List<Integer>> ans) {
        if (sublist.size() == k) {
            ans.add(new ArrayList(sublist));
            return;
        }
        // 使用index, 得到集合的数字会按[1, n]中的顺序排列
        // 如1, 2 -> 3 使用index只会出现1, 2
        //         而不使用index可能出现2, 1, 则解合中包含重复的组合
        // index相当于背包问题的当前index位置数字放与不放
        // 剪枝: 还有k - sublist.size()个空位，所以[1, n]中至少要有k - sublist.size()个元素
        // i最多为n - (k - sublist.size()) + 1(再大就没有足够的空位来找到k个数的组合)
        for (int i = index; i <= n - (k - sublist.size()) + 1; i++) {
            sublist.add(i);
            // i以前的数字已经考虑过了(i之前的数字在之前的组合中已经使用过, 不需要再考虑)
            // i + 1而不是 index + 1(index + 1会重复考虑使用过的数字, important)
            dfs(n, k, i + 1, sublist, ans);
            sublist.remove(sublist.size() - 1); // 回溯
        }
    }
}