/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 * 
 * 1、暴力：计算每个容器的大小
 * 2、双指针：为了使面积最大化，我们需要考虑更长的两条线段之间的区域。
 *    如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
 *    但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
 *    因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小
 */
class Solution {
    public int maxArea1(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxarea = 0;
        for (int i = 0; i < height.length - 1; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int h = Math.min(height[i], height[j]);
                maxarea = Math.max(maxarea, h * (j - i));
            }
        }
        return maxarea;
    }

    public int maxArea2(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxarea = 0, left = 0, right = height.length - 1;
        while (left < right) {
            maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxarea;
    }
}

