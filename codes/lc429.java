import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=429 lang=java
 *
 * [429] N叉树的层序遍历
 * 
 * 题目：返回N叉树的层次遍历(即从左到右，逐层遍历)
 * 
 * 思路：1、leetcode方法，记录每层的节点个数，遍历完后换行
 *      2、左神方法，last记录当前行的最后一个节点，当到达最后一个节点时换行(感觉有点捞，不写了)
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
    public List<List<Integer>> levelOrder1(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int curLevelNodeNums = queue.size();
            for (int i = 0; i < curLevelNodeNums; i++) {
                Node cur = queue.poll();
                list.add(cur.val);
                List<Node> sons = cur.children;
                for (int j = 0; j < sons.size(); j++) {
                    queue.offer(sons.get(j));
                }
            }
            res.add(list);
        }
        return res;
    }
}

