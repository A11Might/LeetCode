import java.util.*;

/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层次遍历
 * 
 * 题目：之字形遍历二叉树
 * 
 * 思路：层次遍历，每次遍历时队列中的元素就是当前行中元素的个数，记录每行节点个数，遍历当前行后换行，逆序遍历下一行
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            // number of elements in the current level
            int levelSize = queue.size();
            LinkedList<Integer> sublist = new LinkedList<>();
            while (levelSize-- > 0) {
                TreeNode cur = queue.poll();
                // fulfill the current level
                // maybe change the dirction of store order(base on flag)
                if (flag) sublist.add(cur.val);
                else sublist.addFirst(cur.val);
                // add child nodes of the current level
                // in the queue for the next level
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            ans.add(sublist);
            // change the direction of store order
            flag = !flag;
        }
        return ans;
    }
}

