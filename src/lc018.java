import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [18] 四数之和
 * 
 * 题目: 在给定包含n个整数的数组中找到所有不重复的满足a + b + c + d == target的四元组
 *
 * 难度: medium
 *
 * 思路: 排序后固定前两个元素, 再用双索引寻找另两个元素, 同[15] 三数之和
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 3)
     * 空间复杂度: O(1)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // 题目要求找出所有不重复的四元组, 排序后方便跳过重复元素
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        // 先固定前两个数, 再在后面的区间内找合为target - nums[i] - nums[j]的两个数
        for (int i = 0; i < len - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { // 跳过重复元素
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) { // 跳过重复元素
                    continue;
                }
                int twoSum = target - nums[i] - nums[j];
                int left = j + 1, right = len - 1;
                while (left < right) {
                    int curSum = nums[left] + nums[right];
                    if (curSum == twoSum) { // 用后面数组最小值加最大值与target - nums[i] - nums[j]比较, 判断两数位置
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left++], nums[right--])); // 命中加入返回列表, 继续寻找
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
        }

        return ans;
    }
}
