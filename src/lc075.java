/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 * 
 * 题意：将只包含0, 1, 2三种元素的数组, 原地排序
 * 
 * 思路: 1. partition
 *      2. 使用计数排序的两趟扫描算法, 先迭代计算出0, 1 和 2 元素的个数, 然后按照0, 1, 2的排序, 重写当前数组
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        int small = -1, big = len;
        int index = 0;
        while (index < big) {
            if (nums[index] == 0) {
                // 当前++small位置为元素1或者第一个未排序元素, 交换后不需要也不能再次判断当前index位置元素
                // 若当前++small位置为第一个未排序元素且其为0, 交换后再次判断当前index位置元素, 会再次进入此条件导致small再次自加并交换, 出现错误
                // 实例[2, 0, 2, 1, 1, 0]
                swap(nums, ++small, index++);
            } else if (nums[index] == 2) {
                // 当前++big位置为未排序元素, 交换后需要再次判断当前index位置元素
                swap(nums, --big, index);
            } else {
                index++;
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
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

