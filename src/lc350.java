import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 * 
 * 题目：找出给定两数组的交集
 *      (输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致；不考虑输出结果的顺序)
 * 
 * 思路：1、查询表map
 *       2、排序后，使用双索引依次比较两数组中元素
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> record = new HashMap<>();
        ArrayList<Integer> resultList = new ArrayList<>();
        // 使用hashmap注册nums1中所有数字
        for (int num : nums1) {
            if (!record.containsKey(num)) {
                record.put(num, 1);
            } else {
                record.put(num, record.get(num) + 1);
            }
        }
        // 遍历nums2中数字，找到交集存入list
        for (int num : nums2) {
            if (record.containsKey(num)) {
                if (record.get(num) == 0) {
                    record.remove(num);
                } else {
                    resultList.add(num);
                    record.put(num, record.get(num) - 1);
                }
            }
        }
        
        int[] res = new int[resultList.size()];
        int index = 0;
        for (int num : resultList) {
            res[index++] = num;
        }
        
        return res;
        
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        // 依次比较两数组中元素
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] res = new int[result.size()];
        int index = 0;
        for (int num : result) {
            res[index++] = num;
        }

        return res;
    }
}

