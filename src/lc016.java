import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=16 lang=java
 *
 * [16] 最接近的三数之和
 * 
 * 题目：在给定包括n个整数的数组中找到和最接近target的三个数
 *       (每组输入只存在唯一答案)
 * 
 * 思路：排序后固定第一个元素，双索引寻找另两个元素
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums); // 排序方便跳过重复元素
        int diff = Math.abs(nums[0] + nums[1] + nums[2] - target); // 当前最接近target的三数之和与target的差值
        int res = nums[0] + nums[1] + nums[2]; // 当前最接近target的三数之和
        for (int i = 0; i < nums.length; i++) { // 先固定第一个数，再在后面的区间内找合最接近0 - nums[i]的两个数
            if (i > 0 && nums[i] == nums[i - 1]) { // 跳过重复元素
                continue;
            }
            int twoSum = target - nums[i];
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                // 用后面数组最小值加最大值与0 - nums[i]比较，判断两数位置
                int curSum = nums[l] + nums[r];
                // 三数之和和为target，最接近直接返回
                if (curSum == twoSum) { 
                    return nums[i] + nums[l] + nums[r];
                // 三数之和不为target
                } else {
                    // 更新当前和最接近target的三个数
                    if (Math.abs(curSum - twoSum) < diff) {
                        diff = Math.abs(curSum - twoSum);
                        res = nums[i] + curSum;
                    }
                    // 移动索引试图使和接近target
                    if (curSum > twoSum) {
                        while (l < r && nums[r] == nums[--r]) {
                        }
                    } else {
                        while (l < r && nums[l] == nums[++l]) {
                        }
                        
                    }
                }
            }
        }

        return res;
    }
}

