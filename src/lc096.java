/*
 * @lc app=leetcode.cn id=96 lang=java
 *
 * [96] 不同的二叉搜索树
 * 
 * 题意：给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种
 *
 * 难度: medium
 * 
 * 思路：动态规划,
 *      假设n个节点存在二叉搜索树的个数是G(n)，分别以1为根节点，2为根节点，...，n为根节点
 *      当1为根节点时，其左子树节点个数为0，右子树节点个数为n-1
 *      同理当2为根节点时，其左子树节点个数为1，右子树节点为n-2
 *      可得状态转移方程: f(n) = f(0) * f(n - 1) + f(1) * f(n - 2) + ... + f(n - 1) * f(0)
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            // f(n) = f(0) * f(n - 1) + f(1) * f(n - 2) + ... + f(n - 1) * f(0)
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}

