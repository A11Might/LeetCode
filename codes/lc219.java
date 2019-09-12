import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=219 lang=java
 *
 * [219] 存在重复元素 II
 * 
 * 题目：在给定的整数数组中，判断能否找到两个不同索引的相同的数，要求索引最大差值的绝对值为k
 *
 * 思路：滑动窗口+查找表set，固定滑动窗口大小为k + 1，若窗口中有相同的数则找到满足条件的两个数
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        HashSet<Integer> record = new HashSet<>(); // 用于注册窗口中的元素值
        int l = 0, r = -1; // 滑动窗口[l, r]
        while (r + 1 < nums.length) {
            r++;
            // 窗口中含有与当前元素值相同的元素
            if (record.contains(nums[r])) {
                return true;
            }
            // 窗口中含有与当前元素值相同的元素
            record.add(nums[r]);
            // 当前窗口已经扩到最大，在下次循环前需要缩小窗口，保证下次窗口扩大时，窗口中元素都没过期
            if (r - l == k) {
                record.remove(nums[l++]);
            }
        }
        
        return false;
    }
}

