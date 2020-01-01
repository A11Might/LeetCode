import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=16 lang=java
 *
 * [16] 最接近的三数之和
 * 
 * 题目: 在给定包括n个整数的数组中返回和最接近target的三个数的和
 *       (每组输入只存在唯一答案)
 *
 * 难度: medium
 * 
 * 思路: 遍历所有三数之和, 找到最接近target的
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(1)
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先进行排序, 方便利用有序特性调整三数之和的大小(同[167] 两数之和 II - 输入有序数组)
        int len = nums.length;
        int ans = nums[0] + nums[1] + nums[2]; // 最接近target的三数之和(为了方便初始化为前三数之和)
        // 先固定第一个数, 再在后面的区间内找另外两个数
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1, right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 实时更新最接近target的三数之和
                ans = Math.abs(target - sum) < Math.abs(target - ans) ? sum : ans;
                // 根据当前和的大小调整后面两个数, 来使三数之和靠近target
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }
}

