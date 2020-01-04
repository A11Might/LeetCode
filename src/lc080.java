/*
 * @lc app=leetcode.cn id=80 lang=java
 *
 * [80] 删除排序数组中的重复项 II
 * 
 * 题目: 删除给定数组中的重复次数超过两次的元素
 *      (使得每个元素最多出现两次, 不需要考虑数组中超出新长度后面的元素)
 *
 * 难度: medium
 * 
 * 思路: 类似partition, 将数组分为重复次数不超过两次的部分和无需考虑的部分
 *       (这类问题需要考虑范式, 即处理题目中2次变为3次4次等等的情况)
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int k = 1; // 重复次数不超过两次的区间[0, k], 初始为两个元素(每个元素最多可是出现两次, 这两个元素相同也符合题意)
        for (int i = 2; i < nums.length; i++) {
            // 当前元素第一次出现或之前只出现一次, 只需比较当前元素与去重区间中nums[k - 1]元素(区间倒数第二个元素)即可, 如下
            // 当前这些是建立在数组有序的情况下的
            // [1, 1, 1, 2, 2, 3]
            //nums[k - 1]       nums[i]
            // [1,           1]   1  , nums[i] == nums[k - 1]
            // [1,           1]   2  , nums[i] != nums[k - 1]
            if (nums[i] != nums[k - 1]) {
                nums[++k] = nums[i];
            }
        }

        // 返回去重后数组长度
        return k + 1;
    }
}