import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 * 
 * 题目: 在给定包含n个整数的数组中找到所有不重复的满足a + b + c == 0的三元组
 *
 * 难度: medium
 * 
 * 思路: 排序后固定第一个元素, 双索引寻找另两个元素
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(1)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 题目要求找出所有不重复的三元组, 排序后方便跳过重复元素
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        // 先固定第一个数, 再在后面的区间内找合为0 - nums[i]的两个数
        for (int i = 0; i < len - 2; i++) {
            // 跳过重复元素, 当前以nums[i]为第一个元素的三元组已全找到
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int twoSum = 0 - nums[i];
            // 同[167] 两数之和 II - 输入有序数组, 不同处仅为找到一组和为twoSum的两个数后
            // 为了避免重复的三元组, 需要跳过与当前值相同的元素
            int left = i + 1, right = len - 1;
            while (left < right) {
                int curSum = nums[left] + nums[right];
                if (curSum == twoSum) { // 用后面数组最小值加最大值与0 - nums[i]比较, 判断两数位置
                    ans.add(Arrays.asList(nums[i], nums[left++], nums[right--])); // 命中加入返回列表, 继续寻找
                    while (left < right && nums[left] == nums[left - 1]) left++; // 跳过重复元素
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (curSum < twoSum) {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }

        return ans;
    }
}
