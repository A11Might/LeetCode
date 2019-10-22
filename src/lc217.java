import java.util.Arrays;
import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=217 lang=java
 *
 * [217] 存在重复元素
 * 
 * 题目：判断给定数组中是否有重复元素
 * 
 * 思路：1、查找表map，统计元素出现次数
 *      2、排序数组
 */
class Solution {
    public boolean containsDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> record = new HashMap<>(); // <元素的值，出现次数>
        for (int num : nums) {
            record.put(num, record.getOrDefault(num, 0) + 1);
            // 出现重复元素
            if (record.get(num) == 2) {
                return true;
            }
        }

        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 出现重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}

