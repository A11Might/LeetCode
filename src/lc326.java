/*
 * @lc app=leetcode.cn id=326 lang=java
 *
 * [326] 3的幂
 *
 * 题目：判断给定整数是否是 3 的幂次方。
 *
 * 难度： easy
 *
 * 思路：1. 循环迭代
 *      2. 3 进制下只有第一位是 1
 *      3. 整数限制
 */
class Solution {
    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public boolean isPowerOfThree1(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     */
    public boolean isPowerOfThree2(int n) {
        if (n <= 0) {
            return false;
        }
        String str = Integer.toString(n, 3); // 转为 3 进制
        return str.lastIndexOf("1") == 0 && str.indexOf("2") == -1; // 首位为 1 且其余位为 0 即为 3 的n次幂
    }

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}