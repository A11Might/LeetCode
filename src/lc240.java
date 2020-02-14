/*
 * @lc app=leetcode.cn id=240 lang=java
 *
 * [240] 搜索二维矩阵 II
 *
 * 题目: 在给定m * n矩阵matrix中搜索目标值target.
 *      (每行的元素从左到右升序排列; 每列的元素从上到下升序排列)
 *
 * 难度: medium
 *
 * 思路: 1. 暴力, 遍历按顺序遍历每一个的元素.
 *      2. 每行升序, 每列升序: 从左下角往右上角遍历(或从右上角往左下角遍历), 当前元素大于目标元素时则向上遍历,
 *         当前元素小于目标元素时则下右遍历, 直至找到目标元素或遍历完矩阵.
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int rows = matrix.length, cols = matrix[0].length;
        // 从左下角往右上角遍历
        int row = rows - 1, col = 0;
        while (row >= 0 && col < cols) {
            // 找到目标元素
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                // 当前元素小于目标元素, 则向右遍历
                col++;
            } else {
                // 当前元素大于目标元素, 则向上遍历
                row--;
            }
        }

        return false;
    }
}

