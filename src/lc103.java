import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层次遍历
 * 
 * 题目：之字形遍历二叉树
 * 
 * 思路：层次遍历，
 *      1、使用变量记录每行的最后一个节点,当到达遍历到该行最后一个节点时，换行逆序存储当前行元素
 *      2、记录每行节点个数，当打印完该行元素后，换行逆序打印下一行(左神的方法没这个简单啊，妈蛋)
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
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode cur = null; // 当前节点
        TreeNode last = root; // 当前节点所在行的最右节点
        TreeNode nLast = null; // 当前节点下一行的最右节点
        boolean flag = true; // 判断当前是正向存储元素，还是逆向存储
        queue.offer(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            // 由flag决定正向还是逆向存储元素
            if (flag) {
                list.add(cur.val);
            } else {
                list.addFirst(cur.val); // <---
            }
            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left; // 实时更新下一行最右节点
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nLast = cur.right; // 实时更新下一行最右节点
            }
            // 当cur == last时，换行并将flag取反，下一行将逆序存储元素
            if (cur == last) {
                flag = !flag;
                last = nLast;
                res.add(list);
                list = new LinkedList<>();
            }
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        boolean flag = true;
        queue.offer(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            // number of elements in the current level
            int nums = queue.size();
            for (int i = 0; i < nums; i++) {
                TreeNode cur = queue.poll();
                // fulfill the current level
                // maybe change the dirction of store order(base on flag)
                if (flag) {
                    list.add(cur.val);
                } else {
                    list.addFirst(cur.val); // <---
                }
                // add child nodes of the current level
                // in the queue for the next level
                if (cur.left != null) {
                    queue.offer(cur.left);
                } 
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(list);
            // change the dirction of store order
            flag = !flag;
        }
        return res;
    }
}

