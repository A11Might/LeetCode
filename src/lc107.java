import java.util.*;

/*
 * @lc app=leetcode.cn id=107 lang=java
 *
 * [107] 二叉树的层次遍历 II
 * 
 * 题目：返回二叉树按层遍历的节点值(从下往上逐层的)
 * 
 * 思路：层次遍历，记录每行节点个数，当打印完该行元素后换行，将包含当前行元素 list
 *      加在返回值 res 列表的最前面即可
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();
        LinkedList<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // number of elements in the current level
            int levelSize = queue.size();
            List<Integer> sublist = new ArrayList<>();
            while (levelSize-- > 0) {
                TreeNode cur = queue.poll();
                // fulfill the current level
                sublist.add(cur.val);
                // add child nodes of the current level
                // in the queue for the next level
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            // store the list from end to start
            ans.addFirst(sublist);
        }
        return ans;
    }
}

