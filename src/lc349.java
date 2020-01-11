import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=349 lang=java
 *
 * [349] 两个数组的交集
 * 
 * 题目: 计算给定两个数组的交集(输出结果中的每个元素一定是*唯一的*, 不考虑输出结果的顺序)
 *
 * 难度: easy
 * 
 * 思路: 查找表set
 */
class Solution {
    /**
     * 时间复杂度: O(n + m)
     * 空间复杂度: O(n + m)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> record = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();
        // 注册nums1中的所有数字
        for (int num : nums1) {
            record.add(num);
        }

        // 遍历nums2找到与num1的交集, 放入set中去重
        for (int num : nums2) {
            if (record.contains(num)) {
                resultSet.add(num);
            }
        }

        int index = 0;
        int[] res = new int[resultSet.size()];
        for (int num : resultSet) {
            res[index++] = num;
        }

        return res; 
    }
}

