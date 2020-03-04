/*
 * @lc app=leetcode.cn id=204 lang=java
 *
 * [204] 计数质数
 *
 * 题目: 统计所有小于非负整数 n 的质数的数量.
 *
 * 难度: easy
 *
 * 思路: 与丑数计算方法相似, 埃拉托斯特尼筛法在每次找到一个素数时, 将能被素数整除的数排除掉.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n + 1];
        int cnt = 0;
        // 从第一个质数开始遍历.
        for (int i = 2; i < n; i++) {
            if (notPrime[i]) continue;
            cnt++;
            // 从 i * i 开始将能被素数整除的数排除掉, 因为如果 k < i, 那么 k * i 在之前就已经被去除过了.
            // 在 k * k, k * (k + 1), ..., k * i 中去除过.
            for (long j = (long) i * i; j < n; j += i) {
                notPrime[(int) j] = true;
            }
        }

        return cnt;
    }
}