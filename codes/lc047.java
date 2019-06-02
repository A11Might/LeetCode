import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 * 
 * 回溯算法
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        process(res, numsList, 0);
        return res;
    }

    private void process(List<List<Integer>> res, List<Integer> numsList, int index) {
        if (index == numsList.size()) {
            res.add(new ArrayList<>(numsList));
        } else {
            HashSet<Integer> set = new HashSet<>(); // 每个index上有一个HashSet，判断该位置是否出现过某个字符
            for (int i = index; i < numsList.size(); ++i) {
                if (!set.contains(numsList.get(i))) {
                    set.add(numsList.get(i));
                    Collections.swap(numsList, i, index); // 为第index位选择一个字符
                    process(res, numsList, index + 1); // 全排剩下的字符
                    Collections.swap(numsList, i, index); // 还原numsList
                }
            }
        }
    }
}

