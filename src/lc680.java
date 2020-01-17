/**
 * @author qhhu
 * @date 2020/1/17 - 10:33
 *
 * [680] 验证回文字符串 II
 *
 * 题目: 给定一个非空字符串, 最多删除一个字符, 判断是否能成为回文字符串
 *
 * 难度: easy
 *
 * 思路: 双指针
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public boolean validPalindrome(String s) {
        char[] chrs = s.toCharArray();
        return validPalindromeCore(chrs, 0, chrs.length - 1, 1);
    }

    private boolean validPalindromeCore(char[] chrs, int left, int right, int time) {
        while (left < right) {
            if (chrs[left] == chrs[right]) {
                // 左右指针上的字符相同
                left++;
                right--;
            } else {
                if (time == 0) {
                    return false;
                } else {
                    // 在递归调用时, 不要使用自增自减(会改变后续递归调用中的值而出现错误)
                    // 当左右指针上的字符不相同时删除字符
                    // 删除左侧字符
                    return validPalindromeCore(chrs, left + 1, right, time - 1) ||
                            // 删除右侧字符
                            validPalindromeCore(chrs, left, right - 1, time - 1);
                }
            }
        }

        return true;
    }
}
