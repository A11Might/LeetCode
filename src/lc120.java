import java.util.List;

/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * 题目: 找出给定三角形自顶向下的最小路径和
 *      (每一步只能移动到下一行中相邻的节点上)
 *
 * 难度: medium
 * 
 * 思路: dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 重复使用一维数组，记录每一层的最小值
        int[] dp = new int[triangle.size() + 1];
        // 自下而上寻找最小路径和
        for (int i = triangle.size() - 1; i >= 0; --i) {
            for (int j = 0; j < triangle.get(i).size(); ++j) {
                // 这里的dp[j]使用的时候是上一层的，赋值之后变成当前层
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

}

 

