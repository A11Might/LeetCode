import java.util.ArrayList;
import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 *
 * 题目：找出给定两数组的交集（输出结果中每个元素出现的次数，应与元素在两个数组中出现的*次数一致*；不考虑输出结果的顺序）
 *
 * 难度：easy
 *
 * 思路：1. 若数组无序，查询表map
 *       2. 若数组有序，使用双索引依次比较两数组中元素
 */
class Solution {
    /**
     * 时间复杂度: O(n + m)
     * 空间复杂度: O(n)
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersect(nums2, nums1); // 使用元素较少的数组建查找表
        // 使用 hashmap 注册 nums1 中所有数字
        HashMap<Integer, Integer> freq = new HashMap<>(); // <num, times>
        for (int num : nums1) freq.put(num, freq.getOrDefault(num, 0) + 1);
        // 遍历 nums2 中数字，找到交集存入 list
        List<Integer> ans = new ArrayList<>();
        for (int num : nums2) {
            if (freq.containsKey(num) && freq.get(num) != 0) {
                ans.add(num);
                freq.put(num, freq.get(num) - 1);
            }
        }
        
        int[] ret = new int[ans.size()];
        int idx = 0;
        for (int num : ans) ret[idx++] = num;
        return ret;
    }

    /**
     * 时间复杂度: O(n + m)
     * 空间复杂度: O(1)
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        // 若数组有序
//        Arrays.sort(nums1);
//        Arrays.sort(nums2);
        int len1 = nums1.length, len2 = nums2.length;
        int i = 0, j = 0;
        ArrayList<Integer> list = new ArrayList<>();
        // 一次比较两数组中的元素
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        int index = 0;
        int[] res = new int[list.size()];
        for (int num : list) {
            res[index++] = num;
        }

        return res;
    }
}

