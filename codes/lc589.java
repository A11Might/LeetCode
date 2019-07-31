import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=589 lang=java
 *
 * [589] N叉树的前序遍历
 * 
 * 题目：前序遍历N叉树
 * 
 * 思路：dfs
 *      1、递归
 *      2、迭代
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return res;
        }
        dfs(root);
        return res;
    }

    private void dfs(Node node) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        for (int i = 0; i < node.children.size(); i++) {
            dfs(node.children.get(i));
        }
    }
    
    public List<Integer> preorder2(Node root) {
        if (root == null) {
            return new ArrayList();
        }
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.add(cur.val);
            if (cur.children != null) {
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    stack.push(cur.children.get(i));
                }
            }
        }

        return res;
    }
}

