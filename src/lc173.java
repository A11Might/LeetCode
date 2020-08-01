import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author qhhu
 * @date 2020/8/1 - 9:33
 * <p>
 * [173] 二叉搜索树迭代器
 * <p>
 * 题目：实现一个使用二叉搜索树根节点初始化的迭代器，调用 next() 将返回二叉搜索树的下一个最小的数
 * （next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度）
 * <p>
 * 难度：medium
 * <p>
 * 思路：
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {

    private Deque<TreeNode> stk = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        while (root != null) {
            stk.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode cur = stk.pop();
        int ret = cur.val;
        cur = cur.right;
        while (cur != null) {
            stk.push(cur);
            cur = cur.left;
        }
        return ret;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stk.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */