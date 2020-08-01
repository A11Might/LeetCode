import java.util.*;

/*
 * @lc app=leetcode.cn id=145 lang=java
 *
 * [145] 二叉树的后序遍历
 * 
 * 题目：返回二叉树的后序遍历
 *
 * 难度：hard
 * 
 * 思路：1. 递归
 *      2. 迭代
 *      3. Morris遍历, 将当前节点记为cur
 *                    a. 如果cur无左孩子, cur向右移动
 *                    b. 如果cur有左孩子, 找到cur左孩子的最右节点, 记为mostRight
 *                       i.  如果mostRight.right指针指向null, 让其指向cur, cur向左移动
 *                       ii. 如果mostRight.right指针指向cur, 让其指回null, cur向右移动
 *                    c. 如果cur为空, morris遍历结束
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
     * 空间复杂度: O(h) (h为二叉树的高度)
     */
    private List<Integer> res = new ArrayList<>();
    public List<Integer> postorderTraversal1(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);
        res.add(node.val);
    }

    /**
     * 改造先序遍历实现后序遍历(左神用两个栈实现后序遍历不太行啊, 直接用链表向前存结点即可)
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public List<Integer> postorderTraversal2_1(TreeNode root) {
        if (root == null) return Collections.emptyList();
        LinkedList<Integer> ans = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 按照中-右-左的顺序遍历树
            // 每遍历一个结点则将其放在上一个结点之前, 则ans中的结点顺序即为左-右-中(后序遍历)
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }

        return ans;
    }

    /**
     * 改造先序遍历实现后序遍历(左神用两个栈实现后序遍历不太行啊, 直接用链表向前存结点即可)
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public List<Integer> postorderTraversal2_2(TreeNode root) {
        if (root == null) return Collections.emptyList();
        LinkedList<Integer> ans = new LinkedList<>();
        Deque<TreeNode> stk = new ArrayDeque<>();
        var cur = root;
        while (cur != null || !stk.isEmpty()) {
            while (cur != null) {
                ans.addFirst(cur.val);
                stk.push(cur);
                cur = cur.right;
            }
            cur = stk.pop().left;
        }

        return ans;
    }

    /**
     * 后序遍历的另一种迭代实现
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    public List<Integer> postorderTraversal2_3(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // 用visited标记打印过的最后一个点, 并初始化为第一个节点(important)
        // 因为当前遍历顺序是后续遍历(顺序为左-右-中), 通过判断当前遍历到的结点的左右子结点是否遍历过来决定当前遍历到的结点是否应该打印.
        // 所以root是第一个遍历的结点, 它会判断它的左右子结点是否遍历, 因而visited可以初始化为root(不会影响判断)
        // 但若将visited初始化为null, 则会漏打节点, 如[1, null , 2, 3]中, 会漏掉3这个节点
        // 因为当cur == 2时, cur.left != null && visited != cur.left但是visited == cur.right == null. 会将3跳过
        TreeNode visited = root;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (cur.left != null && cur.right != visited && cur.left != visited) {
                stack.push(cur.left);
            } else if (cur.right != null && cur.right != visited) {
                stack.push(cur.right);
            } else {
                res.add(stack.pop().val);
                visited = cur;
            }
        }

        return res;
    }

    /**
     * Morris后序遍历, 逆序打印真的第二次回到自己的节点左子树的右边界, 并在函数退出前打印整棵树的右边界
     * 时间复杂度: O(n)
     * 空间复杂度: O(n) (n为树的高度即递归栈的深度)
     */
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal4(TreeNode root) {
        if (root == null) return Collections.emptyList();
        TreeNode cur = root;
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    // 先恢复左子树(防止成环, 进入死循环), 再逆序存储左子树的右边界
                    printEdge(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        // 逆序存储整棵树的右边界
        printEdge(root);
        return ans;
    }

    // 逆序打印当前节点右边界
    private void printEdge(TreeNode node) {
        TreeNode tail = reverseEdge(node);
        TreeNode cur = tail;
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 反转当前节点右边界
    private TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null, cur = from, succ = null;
        while (cur != null) {
            succ = cur.right;
            cur.right = pre;
            pre = cur;
            cur = succ;
        }
        return pre;
    }
}

