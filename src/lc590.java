import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=590 lang=java
 *
 * [590] N叉树的后序遍历
 * 
 * 题目：后序遍历N叉树
 * 
 * 思路：dfs，
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
    public List<Integer> res = new LinkedList<>();
    public List<Integer> postorder1(Node root) {
        if (root == null) {
            return res;
        }
        return dfs(root);
    }

    private List<Integer> dfs(Node node) {
        if (node == null) {
            return null;
        }
        for (int i = 0; i < node.children.size(); i++) {
            dfs(node.children.get(i));
        }
        res.add(node.val);
        return res;
    }

    public List<Integer> postorder2(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new LinkedList<>();
        Deque<Node> stack = new LinkedList<>();
        Deque<Integer> help = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            help.push(cur.val);
            List<Node> sons = cur.children;
            for (int i = 0; i < sons.size(); i++) {
                stack.push(sons.get(i));
            }
        }
        while (!help.isEmpty()) {
            res.add(help.pop());
        }
        return res;
    }
}

