import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [18] 四数之和
 * 
 * 题目：在给定的包含n个整数的数组中找到所有和为target的四个元素的不重复元组
 * 
 * 思路：排序后固定前两个元素，再用双索引寻找另两个元素
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums); // 排序方便跳过重复元素
        int n = nums.length;
        // 先固定前两个数，再在后面的区间内找合为target - nums[i] - nums[j]的两个数
        for (int i = 0; i < n - 3; i++) { 
            if (i > 0 && nums[i] == nums[i - 1]) { // 跳过重复元素
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) { // 跳过重复元素
                    continue;
                }
                int l = j + 1, r = n - 1;
                int twoSum = target - nums[i] - nums[j];
                while (l < r) {
                    int curTwoSum = nums[l] + nums[r];
                    // 用后面数组最小值加最大值与target - nums[i] - nums[j]比较，判断两数位置
                    if (curTwoSum == twoSum) { 
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r])); // 命中加入返回列表，继续寻找
                        while (l < r && nums[l] == nums[++l]) { // 跳过重复元素
                        }
                        while (l < r && nums[r] == nums[--r]) {
                        }
                    } else if (curTwoSum > twoSum) {
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
