import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=297 lang=java
 *
 * [297] 二叉树的序列化与反序列化
 * 
 * 题目：二叉树的序列化与反序列化
 * 
 * 思路：怎么序列化的就怎么反序列化
 *      1、dfs先序遍历序列化与反序列化
 *      2、bfs序列化与反序列化(time limit exceeded，难受啊飞)
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        return dfsSerialize(root);
    }

    private String dfsSerialize(TreeNode node) {
        if (node == null) {
            return "null,";
        }
        String res = node.val + ",";
        res += dfsSerialize(node.left);
        res += dfsSerialize(node.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        String[] values = data.split(",");
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }
        return dfsDeserialize(queue);
    }

    private TreeNode dfsDeserialize(Deque<String> queue) {
        String value = queue.poll();
        if (value.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = dfsDeserialize(queue);
        node.right = dfsDeserialize(queue);
        return node;
    }

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        String res = root.val + ",";
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                res += cur.left.val + ",";
            } else {
                res += "null,";
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                res += cur.right.val + ",";
            } else {
                res += "null,";
            }
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] values = data.split(",");
        int index = 0;
        TreeNode root = generateNodeByString(values[index++]);
        if (root == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.left = generateNodeByString(values[index++]);
            cur.right = generateNodeByString(values[index++]);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return root;
    }

    private TreeNode generateNodeByString(String str) {
        if (str.equals("null")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(str));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

