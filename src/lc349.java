import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=349 lang=java
 *
 * [349] 两个数组的交集
 * 
 * 题目：计算给定两个数组的交集（输出结果中的每个元素一定是*唯一的*，不考虑输出结果的顺序）
 *
 * 难度：easy
 * 
 * 思路：查找表 set
 */
class Solution {
    /**
     * 时间复杂度：O(n + m)
     * 空间复杂度：O(n + m)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> record = new HashSet<>();
        for (int num : nums1) record.add(num); // 注册 nums1 中的所有数字
        HashSet<Integer> ans = new HashSet<>();
        // 遍历 nums2 找到与 num1 的交集，放入 set 中去重
        for (int num : nums2) {
            if (record.contains(num)) ans.add(num);
        }
        
        int[] ret = new int[ans.size()];
        int idx = 0;
        for (int num : ans) ret[idx++] = num;
        return ret; 
    }
}