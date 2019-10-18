import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/18 - 23:35
 *
 * [77] 组合
 *
 * 题目：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
 *
 * 难度：medium
 *
 * 思路：回溯
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(n, 1, k, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(int n, int index, int k, List<Integer> sublist, List<List<Integer>> ans) {
        if (sublist.size() == k) {
            ans.add(new ArrayList<>(sublist));
            return;
        }
        // 剪枝
        // 还有k - sublist.size()个空位，所以[1, n]中至少要有k - sublist.size()个元素
        // i最多为n - (k - sublist.size()) + 1(再大就没有足够的空位来找到k个数的组合)
        for (int i = index; i <= n - (k - sublist.size()) + 1; i++) {
            sublist.add(i);
            // i以前的数字已经考虑过了
            dfs(n, i + 1, k, sublist, ans);
            sublist.remove(sublist.size() - 1);
        }
    }
}
