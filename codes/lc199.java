/**
 * @author qhhu
 * @date 2019/9/25 - 19:58
 *
 * [199] 二叉树的右视图
 *
 * 题目：按照从顶部到底部的顺序打印给定二叉树从右侧所能看到的节点值
 *
 * 难度：medium
 *
 * 思路：用左神的层次遍历方法，记录每一层的最右侧的节点
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        queue.offer(root);
        // last记录当前层的最右侧节点
        // nlast记录当前层的下一层的最右侧节点
        TreeNode last = root;
        TreeNode nLast = null;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                // 实时更新nlast的值
                nLast = cur.left;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                // 实时更新nlast的值
                nLast = cur.right;
            }
            // 当前节点到达当前层的最右侧时，记录当前节点
            // 更新last为nlast，继续遍历下一层
            if (cur == last) {
                res.add(cur.val);
                last = nLast;
            }
        }

        return res;
    }
}