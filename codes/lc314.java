import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [314] 二叉树的垂直遍历(代码未测试)
 * 
 * 题目：返回二叉树按列遍历的节点值(逐列的)
 * 
 * 思路：层序遍历，
 *      给每个结点赋上列号,把根节点给个序号0，然后开始层序遍历，
 *      凡是左子节点则序号减1，右子节点序号加1，这样我们可以通
 *      过序号来把相同列的节点值放到一起
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Deque<TreeNodeAndCol> queue = new LinkedList<>();
        queue.offer(new TreeNodeAndCol(root, 0));
        while (!queue.isEmpty()) {
            TreeNodeAndCol cur = queue.poll();
            int curNodeNum = cur.num;
            TreeNode curTreeNode = cur.node;
            if (map.containsKey(curNodeNum)) {
                map.get(curNodeNum).add(curTreeNode.val);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(curTreeNode.val);
                map.put(curNodeNum, list);
            }
            if (curTreeNode.left != null) {
                queue.offer(new TreeNodeAndCol(curTreeNode.left, curNodeNum - 1));
            }
            if (curTreeNode.right != null) {
                queue.offer(new TreeNodeAndCol(curTreeNode.right, curNodeNum + 1));
            }
        }
        for (Entry<Integer, ArrayList<Integer>> set : map.entrySet()) {
            res.add(set.getValue());
        }
        return res;
    }

    class TreeNodeAndCol {
        public TreeNode node;
        public int num;

        public TreeNodeAndCol(TreeNode node, int num) {
            this.node = node;
            this.num = num;
        }
    }
}
