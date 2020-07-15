/**
 * @author qhhu
 * @date 2020/7/14 - 9:49
 *
 * [116] 填充每个节点的下一个右侧节点指针
 *
 * 题意：给定一个满二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点，如果找不到下一个右侧节点，则将 next 指针设置为 null。
 *
 * 难度：medium
 *
 * 思路：bfs，遍历一行的同时使用 next 指针将下一行连接起来，然后使用 next 指针遍历下一行，继续建立 next 指针。
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Node start = root; // 每行的第一个节点
        // 直到遍历到叶子节点
        while (start.left != null) {
            // 从每行的第一个节点开始，沿着 next 指针遍历本行，同时建立下一行的 next 指针
            for (Node cur = start; cur != null; cur = cur.next) {
                // next 左子节点连右子节点
                cur.left.next = cur.right;
                // next 右子节点连左子节点
                if (cur.next != null) cur.right.next = cur.next.left;
            }
            // 换到下一行
            start = start.left;
        }
        return root;
    }
}
