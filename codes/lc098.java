import java.util.Stack;

/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
 * 
 * 题目：判断二叉树是否是二叉搜索树
 * 
 * 思路：1、树型dp(相对于方法三来说有点蠢，难受啊飞)
 *         情况：a、左子树或右子树不是二叉搜索树
 *              b、左子树和右子树都是二叉搜索树，left.max < head < right.min
 *          信息：a、子树是否是二叉搜索树
 *               b、子树的最大值和最小值
 *       2、二叉搜索树中序遍历顺序为递增的
 *       3、递归每个节点，判断每个节点是否满足二叉搜索树的条件
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
    public boolean isValidBST1(TreeNode root) {
        return process(root).isB;
    }

    private ReturnData process(TreeNode node) {
        // 第三步，定义递归基
        if (node == null) {
            return new ReturnData(true, Long.MIN_VALUE, Long.MAX_VALUE); // [2147483647]
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        ReturnData left = process(node.left); // 左子树的信息
        ReturnData right = process(node.right); // 右子树的信息
        // 左子树或右子树不是二叉搜索树
        if (!left.isB || !right.isB) {
            return new ReturnData(false, 0, 0);
        }
        // 左子树和右子树都是二叉搜索, (node.val <= left.max) | (right.min <= node.val)
        if (node.val <= left.max || right.min <= node.val) {
            return new ReturnData(false, 0, 0);
        }
        // 第二步将黑盒实现
        // 左子树和右子树都是二叉搜索树，left.max < head < right.min
        return new ReturnData(true, 
        Math.max(node.val, Math.max(left.max, right.max)),
        Math.min(node.val, Math.min(left.min, right.min)));
    }

    // 定制返回值
    class ReturnData {
        public boolean isB;
        public long max;
        public long min;

        public ReturnData(boolean isB, long max, long min) {
            this.isB = isB;
            this.max = max;
            this.min = min;
        }
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
        return process(root, Long.MIN_VALUE, Long.MAX_VALUE); // (Long.MIN_VALUE, Long.MAX_VALUE)整棵树的左界和右界
    }

    private boolean process(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return process(node.left, min, node.val) && process(node.right, node.val, max);
    }
}

