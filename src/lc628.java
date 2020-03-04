/**
 * @author qhhu
 * @date 2020/3/4 - 9:03
 *
 * [628]
 *
 * 题目：给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 难度：easy
 *
 * 思路：最大乘积即为最大的三个数的乘积。
 *      需额外考虑数组包含负数的情况：选择最小的两个负数和最大的一个正数是最优的。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}