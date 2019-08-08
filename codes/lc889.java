import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=889 lang=java
 *
 * [889] 根据前序和后序遍历构造二叉树
 * 
 * 题目：返回与给定的前序和后序遍历匹配的任何二叉树(数组中没有重复值)
 * 
 * 思路：前序遍历，中(左1)左右
 *      后序遍历，左(左1)右中
 *      通过前序遍历序列知道根节点和左子树的头结点(左1)，通过找到左1在后序遍历中的位置
 *      将前序遍历和后序遍历序列分为左子树和右子树部分，再递归重复操作
 *      
 * 注意：二叉树为真二叉树(度数不是0就是2)时才能重构，当二叉树左子树或右子树为空时，除去根节点无法确定是左二叉树还是右二叉树
 *      
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        // 用来获取值在postorder中的index
        // 方便用preorder中获得的左1的值将两序列拆分为左子树序列和右子树序列
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return process(postMap, pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    private TreeNode process(Map<Integer, Integer> postMap, int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        if (preStart == preEnd || postStart == postEnd) {  // <-----
            return new TreeNode(pre[preStart]);
        }
        TreeNode node = new TreeNode(pre[preStart]);
        // 由于寻找左1需向后偏移一位，若当前[preStart, preEnd]只有一个元素，需特殊处理
        // 需在使用pre[preStart + 1]之前直接创建返回，防止数组越界错误
        // 这是和前序中序、中序后序的区别
        int nodeleftIndexInPostorder = postMap.get(pre[preStart + 1]); // 左1在postorder中的index
        int offset = nodeleftIndexInPostorder - postStart + 1; // 左子树节点个数
        node.left = process(postMap, pre, post, preStart + 1, preStart + offset, postStart, nodeleftIndexInPostorder);
        node.right = process(postMap, pre, post, preStart + offset + 1, preEnd, nodeleftIndexInPostorder + 1, postEnd - 1);
        return node;
    }
}

