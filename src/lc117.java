/**
 * @author qhhu
 * @date 2020/7/14 - 21:55
 *
 * [117] 填充每个节点的下一个右侧节点指针 II
 *
 * 题意：给定一个满二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点，如果找不到下一个右侧节点，则将 next 指针设置为 null。
 *
 * 难度：medium
 *
 * 思路：与 lc116 类似，不同的是本题的树不是一个满二叉树
 *      bfs：从根节点开始宽度优先遍历，每次遍历一层，从左到右依次遍历每个节点。
 *          遍历时维护下一层节点的链表。对于每个节点，依次判断它的左儿子和右儿子是否在存在，如果存在，则插入下一层链表的末尾。
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
        // 遍历每一行的节点
        while (start != null) {
            // 遍历时维护下一层节点的链表
            Node dummy = new Node(-1), tail = dummy;
            for (Node cur = start; cur != null; cur = cur.next) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
            }
            start = dummy.next;
        }
        return root;
    }
}
