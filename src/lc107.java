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
 *      1、使用变量记录每行的最后一个节点,当到达遍历到该行最后一个节点时
 *      换行，将包含当前行元素list加在返回值res列表的最前面即可
 *      2、记录每行节点个数，当打印完该行元素后换行，将包含当前行元素list
 *      加在返回值res列表的最前面即可(左神的方法没这个简单啊，妈蛋)
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
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode cur = null; // 当前节点
        TreeNode last = root; // 当前节点所在行的最右节点
        TreeNode nLast = null; // 当前节点下一行的最右节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left; // 实时更新下一行最右节点
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nLast = cur.right; // 实时更新下一行最右节点
            }
            // 当cur == last时，将包含当前行元素list加在返回值res列表的最前面
            // 即可达到从下往上逐层的逐层遍历的目的
            if (cur == last) {
                res.addFirst(list);
                list = new LinkedList<>();
                last = nLast;
            }
        }
        return res;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<List<Integer>> res = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            // number of elements in the current level
            int nums = queue.size();
            for (int i = 0; i < nums; i++) {
                TreeNode cur = queue.poll();
                // fulfill the current level
                list.add(cur.val);
                // add child nodes of the current level
                // in the queue for the next level
                if (cur.left != null) {
                    queue.offer(cur.left);
                } 
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // store the list from end to start 
            res.addFirst(list); // <---
        }
        return res;
    }
}

