import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=95 lang=java
 *
 * [95] 不同的二叉搜索树 II
 * 
 * 题目：生成所有[1, n]为节点的所有二叉搜索树
 *
 * 难度: medium
 * 
 * 思路：树型DP,
 *       情况：以[1, n]中每个数字为头节点，左侧所有二叉搜素树组合和右侧所有二叉搜素树组合再组合，即为所有情况
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
    /**
     * 时间复杂度: O(4 ^ n / n ^ (1 / 2))
     * 空间复杂度: O(4 ^ n / n ^ (1 / 2))
     * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/bu-tong-de-er-cha-sou-suo-shu-ii-by-leetcode/
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTreesCore(1, n);
    }

    private List<TreeNode> generateTreesCore(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        // 处理两个边界条件:
        // 当i == start时, generateTreesCore(start, i - 1)
        // 和当i == end时, generateTreesCore(i + 1, end)
        // 当前子树没有节点, 为null
        if (start > end) {
            ans.add(null);
            return ans;
        }
        // 分别以[start, end]为头节点
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = generateTreesCore(start, i - 1); // 头节点左侧节点组成的所有二叉搜素树组合
            List<TreeNode> rightSubtrees = generateTreesCore(i + 1, end); // 头节点右侧节点组成的所有二叉搜素树组合
            // 以i为头节点，组合头节点左侧节点组成的所有二叉搜素树组合和头节点右侧节点组成的所有二叉搜素树组合
            for (TreeNode leftSubtreeRoot : leftSubtrees) {
                for (TreeNode rightSubtreeRoot : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubtreeRoot;
                    root.right = rightSubtreeRoot;
                    ans.add(root);
                }
            }
        }

        return ans;
    }
}
