import java.util.*;

/**
 * @author qhhu
 * @date 2019/10/10 - 12:31
 *
 * [257] 二叉树的所有路径
 *
 * 题目：返回给定二叉树从根节点到叶子结点的路径
 *
 * 难度：easy
 *
 * 思路：同lc112路径总和
 *      1、递归,
 *           如果当前的节点是叶子节点，则在当前的路径末尾添加该节点后，就得到了一条从根节点到
 *           叶子节点的路径，可以把该路径加入到答案中
 *           如果当前的节点不是叶子节点，则在当前的路径末尾添加该节点，并递归遍历该节点的每一
 *           个孩子节点
 *      2、迭代，bfs(队列中方的是node, path)，若当前节点是叶子节点，则将它对应的路径加入到答案中
 *      3、波波老师的递归
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
    public List<String> binaryTreePaths1(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        dfs(root, "", paths);
        return paths;
    }

    private void dfs(TreeNode root, String path, LinkedList<String> paths) {
        if (root != null) {
            path += Integer.toString(root.val); // string基础类型变量一样，参数传递的是拷贝份
            if ((root.left == null) && (root.right == null)) // 当前节点是叶子节点
                paths.add(path); // 把路径加入到答案中
            else {
                path += "->"; // 当前节点不是叶子节点，继续递归遍历
                dfs(root.left, path, paths);
                dfs(root.right, path, paths);
            }
        }
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<String> pathQueue = new ArrayDeque<>(); // 对应nodeQueue中节点到达根节点的路径
        nodeQueue.offer(root);
        pathQueue.offer(root.val + "");
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            String curPath = pathQueue.poll();
            // 到达叶结点
            if (curNode.left == null && curNode.right == null) {
                ans.add(curPath);
            }
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                pathQueue.offer(curPath + "->" + curNode.left.val);
            }
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                pathQueue.offer(curPath + "->" + curNode.right.val);
            }
        }

        return ans;
    }

    // 波波老师的递归
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // 当前节点是叶子结点得到一条路径
        if (root.left == null && root.right == null) {
            ans.add(String.valueOf(root.val));
        }
        // 递归得到当前节点左子树的路径
        // 再依次遍历路径和当前节点组合，得到完整路径
        List<String> left = binaryTreePaths(root.left);
        for (String str : left) {
            ans.add(root.val + "->" + str);
        }
        // 同理
        List<String> right = binaryTreePaths(root.right);
        for (String str : right) {
            ans.add(root.val + "->" + str);
        }

        return ans;
    }
}
