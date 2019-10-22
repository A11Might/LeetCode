/*
 * @lc app=leetcode.cn id=240 lang=java
 *
 * [240] 搜索二维矩阵 II
 * 
 * 1、暴力，遍历按顺序遍历每一个的元素
 * 2、每行升序，每列升序：从左下往右上遍历或从右上往左下遍历
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = matrix.length - 1, j = 0; i >= 0 && j < matrix[0].length;) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}

