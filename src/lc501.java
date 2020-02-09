import java.util.ArrayList;
import java.util.List;
/**
 * @author qhhu
 * @date 2020/2/9 - 9:15
 *
 * [501] 二叉搜索树中的众数
 *
 * 题目: 给定一个含有相同值的二叉搜索树, 找到BST中的所有众数(出现频率最高的元素)
 *      (给定二叉搜索树: 当前结点的左子树包含的所有结点值小于等于当前结点
 *                   当前结点的右子树包含的所有结点值大于等于当面结点;
 *       若众数超过1个, 不需要考虑输出顺序)
 *
 * 难度: easy
 *
 * 思路: 中序遍历给定的二叉搜索树(中序序列是升序, 值相同的结点相邻), 比较当前结点和前一个结点的值, 统计出现次数并更新众数
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
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    private TreeNode preNode = null;
    private int cnt = 0;
    private int maxFreq = 0;
    public int[] findMode(TreeNode root) {
        List<Integer> modes = new ArrayList<>();
        dfs(root, modes);
        // 输出数组形式的答案
        int[] ans = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            ans[i] = modes.get(i);
        }

        return ans;
    }

    private void dfs(TreeNode root, List<Integer> modes) {
        if (root == null) return;
        dfs(root.left, modes);
        // 当当前结点与前一个结点的值相同时, 计数器加一
        if (preNode != null && root.val == preNode.val) {
            cnt++;
        } else {
            // 当之前节点为空或者当前结点与前一个结点的值不相同时, 重新计数
            cnt = 1;
        }
        // 更新前一个遍历的结点(当前结点与之前结点相同时更新并不会有影响)
        preNode = root;
        // 更新众数
        if (cnt > maxFreq) {
            maxFreq = cnt;
            modes.clear();
            modes.add(root.val);
        } else if (cnt == maxFreq) {
            modes.add(root.val);
        }
        dfs(root.right, modes);
    }
}
