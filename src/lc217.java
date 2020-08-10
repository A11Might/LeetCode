import java.util.Arrays;
import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=217 lang=java
 *
 * [217] 存在重复元素
 * 
 * 题目：返回给定数组中是否有重复元素
 *
 * 难度：easy
 * 
 * 思路：1. 查找表set, 记录出现过得元素
 *       2. 排序数组, 相同元素相邻
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> dict = new HashSet<>();
        for (int num : nums) {
            // 出现重复元素
            if (dict.contains(num)) return true;
            dict.add(num);
        }

        return false;
    }

    /**
     * 时间复杂度：O(n * logn)
     * 空间复杂度：O(1)
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 出现重复元素
            if (i != 0 && nums[i] == nums[i - 1]) return true;
        }

        return false;
    }
}

