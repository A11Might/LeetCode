import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 *
 * 题目：在数组中查找两个数使其和为目标值，返回它们的下标
 * 
 * 思路：
 * 1、暴力，两层遍历
 * 2、用哈希表，省去一层遍历
 */
class Solution {
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return new int[] {};
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int aim = target - nums[i];
            if (map.containsKey(aim)) {
                return new int[] {map.get(aim), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
