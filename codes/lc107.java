import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=107 lang=java
 *
 * [107] 二叉树的层次遍历 II
 * 
 * 题目：返回二叉树按层遍历的节点值(从下往上逐层的)
 * 
 * 思路：层次遍历，
 *      使用变量记录每行的最后一个节点,当到达遍历到该行最后一个节点时，换行
 *      将包含当前行元素list加在返回值res列表的最前面即可逆序层次遍历
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode cur = null;
        TreeNode last = root;
        TreeNode nLast = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nLast = cur.right;
            }
            if (cur == last) {
                res.addFirst(list);
                list = new LinkedList<>();
                last = nLast;
            }
        }
        return res;
    }
}

