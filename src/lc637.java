import java.util.*;

/**
 * @author qhhu
 * @date 2020/2/7 - 9:18
 *
 * [637] 二叉树的层平均值
 *
 * 题目: 返回给定非空二叉树每层结点平均值组成的数组
 *
 * 难度: easy
 *
 * 思路: 按层遍历, 统计每层结点的总和
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
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 统计每层的结点总和
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ans.add(sum / size);
        }

        return ans;
    }
}
