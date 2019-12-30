import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 *
 * 题目: 在数组中查找两个数使其和为目标值，返回它们的下标
 *
 * 难度: easy
 * 
 * 思路: 1. 遍历每个元素x, 并查找是否存在一个值与target - x相等的目标元素
 *       2. 迭代的将元素插入到哈希表中的同时, 检查表中是否已经存在加上当前元素和等于目标元素的元素
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int aim = target - nums[i];
            for (int j = i + 1; j < len; j++) {
                if (nums[j] == aim) {
                    return new int[] {i, j};
                }
            }
        }

        throw new IllegalArgumentException("No solution");
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 存入表中和未存入表中的数据存在先后关系, 不会重复使用
            int aim = target - nums[i];
            if (dict.containsKey(aim)) {
                return new int[] {dict.get(aim), i};
            }
            dict.put(nums[i], i);
        }

        throw new IllegalArgumentException("No solution");
    }
}
