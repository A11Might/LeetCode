/*
 * @lc app=leetcode.cn id=204 lang=java
 *
 * [204] 计数质数
 *
 * 题目：统计所有小于非负整数 n 的质数的数量。
 *
 * 难度：easy
 *
 * 思路：线性筛法
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int countPrimes(int n) {
        boolean[] st = new boolean[n];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (!st[i]) primes.add(i);
            for (int j = 0; i * primes.get(j) < n; j++) {
                st[i * primes.get(j)] = true;
                if (i % primes.get(j) == 0) break;
            }
        }
        return primes.size();
    }
}