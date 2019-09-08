import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=95 lang=java
 *
 * [95] 不同的二叉搜索树 II
 * 
 * 题目：生成所有[1, n]为节点的所有二叉搜索树
 * 
 * 思路：树型DP
 *       情况：以[1, n]中每个数字为头结点，左侧所有二叉搜素树组合和
 *             右侧所有二叉搜素树组合再组合，即为所有情况
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return process(1, n);
    }

    private List<TreeNode> process(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = process(start, i - 1);
            List<TreeNode> right = process(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    cur.right = r;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
