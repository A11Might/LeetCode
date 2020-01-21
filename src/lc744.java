/**
 * @author qhhu
 * @date 2020/1/21 - 10:51
 *
 * [744] 寻找比目标字母大的最小字母
 *
 * 题目: 在给定一个自包含小写字母的有序数组letters中查找比目标字母target大的最小字母
 *      (数组中的字母的顺序是循环的, 如[a, b]找z, 则返回a)
 *
 * 难度: easy
 *
 * 思路: 二分查找, 返回大于target的第一个字母
 */
class Solution {
    /**
     * 时间复杂度: O(logn)
     * 空间复杂度: O(1)
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        int lo = 0, hi = len; // 区间[0, len)
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 将区间分为 <= 和 > 部分, 返回 > 部分第一个字符
            if (letters[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        // 数组中的字母的顺序是循环的, 若letters中无大于target的字母, 则返回letters中的第一个字母(即比目标字母target大的最小字母)
        return hi == len ? letters[0] : letters[hi];
    }
}
