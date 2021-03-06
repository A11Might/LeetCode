import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=202 lang=java
 *
 * [202] 快乐数
 * 
 * 题目：判断给定的数字是不是快乐数
 *      (一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的
 *      平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如
 *      果可以变为 1，那么这个数就是快乐数)
 * 
 * 难度：easy
 * 
 * 思路：查找表set，用于判断当前数是否出现过
 */
class Solution {
    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(n)
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            int ans = 0;
            while (n != 0) {
                int t = n % 10;
                ans += t * t;
                n /= 10;
            }
            if (set.contains(ans)) return false;
            set.add(ans);
            n = ans;
        }
        return true;
    }
}