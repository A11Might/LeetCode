import java.util.List;

/*
 * @lc app=leetcode.cn id=139 lang=java
 *
 * [139] 单词拆分
 * 
 * 题意：给定一个非空字符串s判断能否拆分成包含非空单词列表的字典wordDict中的单词(可以重复使用字典中的单词)
 *
 * 难度: medium
 * 
 * 思路：动态规划, 若能将给定字符串s拆为符合要求的子问题s1和s2，则s符合条件
 *      状态转移方程, f(i) = f(j) && substring(j, i) in dict, j in [0, i)
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true; // 空字符串总是字典的一部分
        // 通过j将字符串s.substring(0, i)拆分为s.substring(0, j)和s.substring(j, i)
        // 左闭右开
        for (int i = 1; i <= len; i++) { // 当i == 1时开始拆分(i == 0时为空字符初始为true了)
            for (int j = 0; j < i; j++) {
                // 若s.substring(0, j)和s.substring(j, i)符合条件, 则s.substring(0, i)符合条件
                // 当判断从0到i的子串是否在词典中时, 需要dp[j]为true(此时与它无关), 额外设置dp[0]为true, 是方便这里的判断(vip)
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}

