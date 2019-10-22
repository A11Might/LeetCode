/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 * 
 * 题意：将只包含0, 1, 2三种元素的数组，原地排序
 * 
 * 思路：
 * 1、partition
 * 2、使用计数排序的两趟扫描算法，先迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组
 */
class Solution {
    public void sortColors1(int[] nums) {
        int n = nums.length;
        int zero = -1, one = 0, two = n;
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            } else {
                one++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }

    public void sortColors2(int[] nums) {
        int zero = 0;
        int one  = 0;
        for (int num : nums) {
            if (num == 0) zero++;
            if (num == 1) one++;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (zero > 0) {
                nums[i] = 0;
                zero--;
            } else if (one > 0) {
                nums[i] = 1;
                one--;
            } else nums[i] = 2;
        }
    }
}

