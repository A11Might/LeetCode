import java.util.HashSet;

/**
 * @author qhhu
 * @date 2019/12/30 - 9:56
 *
 * [653] 两数之和 IV - 输入BST
 *
 * 题目: 给定一个二叉搜索树和一个目标值, 判断BST中是否存在两个和为给定值的元素
 *
 * 难度: easy
 *
 * 思路: 1. 同[1] 两数之和, 遍历(先序遍历或者层次遍历, 这样存入表中和未存入表中的数据存在先后关系, 才不会重复使用)
 *          将元素插入到表中的同时, 检查表中是否已经存在加上当前元素和等于目标元素的元素
 *      2. BST中序遍历序列是升序, 解法同[167] 两数之和 II - 输入有序数组, 使用中序遍历得到有序列表之后, 再利用双指针对数组进行查找(get(index))
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
    public boolean findTarget(TreeNode root, int k) {
        return findTargetCore(root, k, new HashSet());
    }

    private boolean findTargetCore(TreeNode root, int k, HashSet<Integer> dict) {
        if (root == null) return false;
        if (dict.contains(k - root.val)) return true;
        dict.add(root.val);
        return findTargetCore(root.left, k, dict) || findTargetCore(root.right, k, dict);
    }
}
