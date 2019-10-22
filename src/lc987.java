import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=987 lang=java
 *
 * [987] 二叉树的垂序遍历
 * 
 * 题目：返回二叉树按列遍历的节点值(逐列按行顺序遍历)
 * 
 * 思路：层序遍历每个节点
 *      给每个结点赋上行号和列号，root节点位置记为(0, 0)
 *      行号，逐行递增；列号，左子节点则行号减1，右子节点则列号加1
 *      将编辑完位置的节点，放入指定比较器的优先级队列中排序
 *      最后按列号拿出即可
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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 层序遍历每个节点，并给每个结点赋上行号和列号，放入指定比较器的优先级队列中排序
        Queue<TreeNodeAndPos> sortedQueue = new PriorityQueue<>(new myComparator());
        Deque<TreeNodeAndPos> queue = new LinkedList<>();
        queue.offer(new TreeNodeAndPos(root, 0, 0));
        while (!queue.isEmpty()) {
            TreeNodeAndPos curMessage = queue.poll();
            sortedQueue.offer(curMessage);
            TreeNode cur = curMessage.node;
            int row = curMessage.row;
            int col = curMessage.col;
            // 行号，逐行递增；列号，左子节点则行号减1
            if (cur.left != null) {
                queue.offer(new TreeNodeAndPos(cur.left, row + 1, col - 1));
            }
            // 行号，逐行递增；列号，右子节点则列号加1
            if (cur.right != null) {
                queue.offer(new TreeNodeAndPos(cur.right, row + 1, col + 1));
            }
        }
        // 按列号拿出即可
        int preCol = sortedQueue.peek().col; // 之前行的行号
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        while (!sortedQueue.isEmpty()) {
            TreeNodeAndPos curMessage = sortedQueue.poll();
            // 当行号变化时，说明遍历到下一行，换行
            if (preCol != curMessage.col) {
                res.add(list);
                preCol = curMessage.col;
                list = new LinkedList<>();
            }
            list.add(curMessage.node.val);
        }
        res.add(list); // 将最后一行的值加入返回值
        return res;
    }

    // 定制比较器
    class myComparator implements Comparator<TreeNodeAndPos> {
        
        @Override
        public int compare(TreeNodeAndPos o1, TreeNodeAndPos o2) {
            // 列数相同，行数相同时，按节点值从小到大排列
            if (o1.col == o2.col) {
                if (o1.row == o2.row) {
                    return o1.node.val - o2.node.val;
            // 列数相同，行数不同时，按行数从小到大排列
                } else {
                    return o1.row - o2.row;
                }
            // 列数不同时，按列数从小到大排列
            } else {
                return o1.col - o2.col;
            }
        }
        
    }

    // 定制含节点及其位置的节点类
    class TreeNodeAndPos {
        public TreeNode node;
        public int row;
        public int col;

        public TreeNodeAndPos(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}

