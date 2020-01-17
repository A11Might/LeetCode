/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 *
 * 题目: 将给定的有序数组num2合并到给定的有序数组num1中, 使得num1成为一个有序数组
 *
 * 难度: easy
 *
 * 思路: 类似归并排序的merge, 但不申请额外空间(题意nums1可以放下nums2中的元素), 在nums1上从后往前排序(从大到小)
 *      牛批，思维定式一直想申请额外空间或从前排序
 *      现在不会了, 想到从前往后排序需要额外移动后面未排序的部分, 就会想到从后往前排序
 */
class Solution {
    /**
     * 时间复杂度: O(m + n)
     * 空间复杂度: O(1)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int index = nums1.length - 1; // 合并后数组的最后一个有效元素下标
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] >= nums2[index2]) {
                nums1[index--] = nums1[index1--];
            } else {
                nums1[index--] = nums2[index2--];
            }
        }
        if (index1 < 0) {
            // 若nums2中仍有未合并元素
            while (index2 >= 0) {
                nums1[index--] = nums2[index2--];
            }
        }
        // 若nums1中仍有未合并元素, 无需操作
    }

    /**
     * 时间复杂度: O(m + n)
     * 空间复杂度: O(1)
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int index = nums1.length - 1; // 合并后数组的最后一个有效元素下标
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                // 若nums2中仍有未合并元素
                nums1[index--] = nums2[index2--];
            } else if (index2 < 0) {
                // 若nums1中仍有未合并元素, 无需操作
                return;
            } else if (nums1[index1] >= nums2[index2]) {
                nums1[index--] = nums1[index1--];
            } else {
                nums1[index--] = nums2[index2--];
            }
        }
    }
}
