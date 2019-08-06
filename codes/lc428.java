import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=428 lang=java
 *
 * [428] 序列化和反序列化 N 叉树(未验证)
 * 
 * 题目：N 叉树序列化和反序列化 
 * 
 * 思路：同[297] 二叉树的序列化与反序列化递归方法(关键在于加入children个数)
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        return dfsSerialize(root);
    }

    private String dfsSerialize(Node node) {
        if (node == null) {
            return "#_";
        }
        String res = node.val + "_";
        int sonsNum = node.children.size(); // 关键在于加入children个数
        res += sonsNum + "_";
        for (int i = 0; i < sonsNum; i++) {
            res += dfsSerialize(node.children.get(i));
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] values = data.split("_");
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }
    }

    private Node dfsDeserialize(Deque<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        int sonsNum = Integer.valueOf(queue.poll()); // 确定有多少个children
        Node node = new Node(Integer.valueOf(value), new LinkedList<>());
        for (int i = 0; i < sonsNum; i++) {
            node.children.set(0, dfsDeserialize(queue));
        }
        return node;
    }
}
