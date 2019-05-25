/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 * 
 * 双重递归：首先先序递归遍历每个节点，再以每个节点作为起始点递归寻找满足条件的路径
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSumEqualsTargetTimes;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        toSum(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return pathSumEqualsTargetTimes;
    }

    private void toSum(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        sum -= node.val;
        if (sum == 0) { // 等于零继续递归(val == 0的情况)
            pathSumEqualsTargetTimes++;
        }
        toSum(node.left, sum);
        toSum(node.right, sum);
    }
}

