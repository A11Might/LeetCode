import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 * 
 * 回溯算法
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
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
            for (int i = index; i < numsList.size(); ++i) {
                Collections.swap(numsList, i, index); // 为第index位选择一个字符
                process(res, numsList, index + 1); // 全排剩下的字符
                Collections.swap(numsList, i, index); // 还原numsList
            }
        }
    }
}

