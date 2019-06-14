import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 * 
 * 题意：返回一个不含重复元素的整数数组所有可能的子集
 * 
 * 思路：
 * 1、回溯(类似背包问题放与不放)
 * 2、遇到一个数就把所有子集加上该数组成新的子集，遍历完毕即是所有子集
 */
class Solution {
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        process(nums, 0, res, new ArrayList<>());
        return res;
    }

    private void process(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            // 加入当前元素
            temp.add(nums[index]);
            process(nums, index + 1, res, temp);
            temp.remove(temp.size() - 1);
            // 不加入当前元素
            process(nums, index + 1, res, temp);
        }
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // 先加入空集
        // 遇到一个数就把所有子集加上该数组成新的子集
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; ++i) {
            int size = res.size();
            for (int j = 0; j < size; ++j) {
                List<Integer> subset = new ArrayList<>(res.get(j));
                subset.add(nums[i]);
                res.add(subset);
            }
        }

        return res;
    }
}

