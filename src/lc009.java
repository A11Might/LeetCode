/*
 * @lc app=leetcode.cn id=9 lang=java
 *
 * [9] 回文数
 * 
 * 题目: 判断一个整数是否是回文数
 *      (不能将整数转为字符串)
 *
 * 难度: easy
 * 
 * 思路: 使用逐步取余的方法反转整数, 为了避免数字反转可能导致的溢出问题, 只反转x数字的一半, 然后判断前后两步分是否相同
 */
class Solution {
    /**
     * 时间复杂度: O(logn) (每次迭代都会将输入除以10, 最多除log _10 (n)次)
     * 空间复杂度: O(1)
     */
    public boolean isPalindrome(int x) {
        // 特殊情况:
        // 如上所述, 当 x < 0 时, x 不是回文数.
        // 同样地, 如果数字的最后一位是 0, 为了使该数字为回文,
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性(important)
        if (x < 0) return false;
        if (x % 10 == 0 && x != 0) return false;

        int inverseNum = 0;
        // 我们将原始数字除以 10, 然后给反转后的数字乘上 10, 如下:
        // 1221 |
        // 122 | 1
        // 12 | 21
        // 所以, 当原始数字小于反转后的数字时, 就意味着我们已经处理了一半位数的数字
        // 12 | 321: x = 12, inverseNum = 123(可以通过x == inverseNum / 10将数字3去除)
        while (x > inverseNum) {
            inverseNum = (inverseNum * 10) + (x % 10);
            x /= 10;
        }

        // 当数字长度为奇数时, 我们可以通过 revertedNumber / 10 去除处于中位的数字.
        // 例如, 当输入为 12321 时, 在 while 循环的末尾我们可以得到 x = 12, revertedNumber = 123,
        // 由于处于中位的数字不影响回文(它总是与自己相等), 所以我们可以简单地将其去除.
        return x == inverseNum || x == inverseNum / 10;
    }
}

