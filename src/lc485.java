/**
 * @author qhhu
 * @date 2020/2/13 - 10:22
 *
 * [485] 最大连续1的个数
 *
 * 题目: 给定一个二进制数组, 返回其中最大连续1的个数.
 *      (数组只包含0和1)
 *
 * 难度: easy
 *
 * 思路: 遍历数组, 统计连续1的个数, 返回最大值
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cnt = 0;
        for (int num : nums) {
            // 若遇到0则重新开始计数
            cnt = num == 0 ? 0 : cnt + 1;
            max = Math.max(max, cnt);
        }

        return max;
    }
}
