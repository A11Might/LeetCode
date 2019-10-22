import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1028 lang=java
 *
 * [1028] 从先序遍历还原二叉树
 * 
 * 题目：根据指定格式的先序遍历序列，还原二叉树
 *      (遍历中的每个节点时，输出D条短划线(D是该节点的深度)然后输出该节点的值)
 *      (若只有一个节点，则其为左子节点)
 * 
 * 思路：考验coding能力，把逻辑转化为代码即可
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
    public TreeNode recoverFromPreorder(String S) {
        // cook the string for store the value and depth in order
        // the store order is very important for create binary tree structrue
        List<Integer> values = new ArrayList<>();
        List<Integer> depths = new ArrayList<>();
        int curValue = 0, curDepth = 0;
        // this flag is mark in count depth or not
        boolean flag = false;
        for (int i = 0; i < S.length(); i++) {
            char curChar = S.charAt(i);
            if (curChar == '-') {
                // make the value before traverse this char
                // so store the value, depth and reset them
                // then count depth
                if (!flag) {
                    values.add(curValue);
                    depths.add(curDepth);
                    curValue = 0;
                    curDepth = 0;
                    flag = true;
                }
                curDepth++;
            } else {
                flag = false;
                curValue *= 10;
                curValue +=  curChar - '0';
            }
        }
        // store the last value and depth
        values.add(curValue);
        depths.add(curDepth);
        // accroding the lists to create binary tree structrue
        // this map is store the node and depth for easy to search the node depth
        Map<TreeNode, Integer> nodeDepth = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(values.get(0));
        // index is the position current traverse
        int index = 1; 
        stack.push(root);
        nodeDepth.put(root, 0);
        while (index < values.size()) {
            // cur node maybe use again so peek it
            TreeNode cur = stack.peek();
            // the node ready to create is the next level of cur node
            // and cur node have none of left and right
            if ((depths.get(index) == (nodeDepth.get(cur) + 1)) && 
                (cur.left == null || cur.right == null)) {
                // create node and store the node and depth in nodeDepth
                TreeNode children = new TreeNode(values.get(index++));
                // the index add itself so you know
                nodeDepth.put(children, nodeDepth.get(cur) + 1); 
                // create the structure
                if (cur.left == null) {
                    cur.left = children;
                } else {
                    cur.right = children;
                }
                // push the node in stack
                stack.push(children);
            } else {
                // doesn't satisfy the condition above
                // pop cur node
                stack.pop();
            }
        }
        return root;
    }
}

