import java.util.LinkedList;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
 * 
 * 题目：判断二叉树是否是二叉搜索树
 *
 * 难度：medium
 * 
 * 思路：1、树型dp(相对于方法三来说有点蠢，难受啊飞)
 *         情况：a、左子树或右子树不是二叉搜索树
 *              b、左子树和右子树都是二叉搜索树，left.max < head < right.min
 *          信息：a、子树是否是二叉搜索树
 *               b、子树的最大值和最小值
 *       2、二叉搜索树中序遍历顺序为递增的
 *       3、递归每个节点，判断每个节点是否满足二叉搜索树的条件
 *       4、3的迭代版本
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
    public boolean isValidBST(TreeNode root) {
        long[] msg = isValidBSTCore(root);

        return msg[0] == 1;
    }

    // return long[]
    // long[0] isBST or not(0 is no, 1 is yes), long[1] subTree's min value, long[2] subTree's max value
    private long[] isValidBSTCore(TreeNode root) {
        // 第三步，定义递归基
        if (root == null) {
            // 当前节点为空时，没有最大，最小值
            // 将最小值置为Long.MAX_VALUE，最大值置为Long.MIN_VALUE避免其参与黑盒实现的比较
            return new long[] {1, Long.MAX_VALUE, Long.MIN_VALUE}; // 设置为long，实例[2147483647]
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        long[] lmsg = isValidBSTCore(root.left); // 左子树的信息
        long[] rmsg = isValidBSTCore(root.right); // 右子树的信息
        // 左子树或右子树不是二叉搜索树
        if (lmsg[0] == 0 || rmsg[0] == 0) {
            // 当判断任意一子树不平衡时，将isBST置为0返回即可(最大，最小值设置无所谓，碰到isBST == 0即会返回)
            return new long[] {0, 0, 0};
        }
        // 左子树和右子树都是二叉搜索，但(node.val <= left.max) | (right.min <= node.val)
        if (root.val <= lmsg[2] || rmsg[1] <= root.val) {
            return new long[] {0, 0, 0};
        }

        // 第二步将黑盒实现
        // 左子树和右子树都是二叉搜索树，left.max < head < right.min
        // 下述比较为选出叶子节点的最大，最小值(叶子节点的左右子树信息都为{1, Long.MAX_VALUE, Long.MIN_VALUE})
        // 非叶结点子树的最小值为左子树的最小值，最大值为右子树的最大值
        return new long[] {1, Math.min(root.val, lmsg[1]), Math.max(root.val, rmsg[2])};
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        long preNum = Long.MIN_VALUE; // 防止val为Integer.MIN_VALUE导致相等，误判。实例 [-2147483648,null,2147483647]
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (preNum >= cur.val) {
                    return false;
                }
                preNum = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }

    public boolean isValidBST3(TreeNode root) {
        return dfs(root, null, null);
    }

    public boolean dfs(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        if ((lower != null && node.val <= lower) || (upper != null && node.val >= upper)) {
            return false;
        }

        return dfs(node.right, node.val, upper) && dfs(node.left, lower, node.val);
    }

    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(), lowers = new LinkedList();
    public boolean isValidBST4(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }
}

