import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author qhhu
 * @date 2020/2/7 - 9:19
 *
 * [513] 找树左下角的值
 *
 * 题目: 返回给定二叉树最后一行最左边的值
 *
 * 难度: medium
 *
 * 思路: 1. 从左往右层次遍历, 维护一个保存每行最左边的值的变量, 并随着遍历不断更新
 *      2. 从右往左层次遍历, 则遍历到的最后一个结点, 即为给定二叉树最后一行最左边的结点
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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            // 从右往左层次遍历
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (root.left != null) {
                queue.offer(root.left);
            }
        }

        return root.val;
    }
}
