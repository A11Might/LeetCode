import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=331 lang=java
 *
 * [331] 验证二叉树的前序序列化
 * 
 * 题目：判断一串以逗号分隔的序列是否是二叉树的前序序列
 * 
 * 思路：1、耗神，一棵树总是以#(空)封口，看出口数和封口数是否相等
 *         根节点有两个出口，每多个非空节点则会多一个出口，每个空节点为一个封口
 *      2、入度和出度，一个正确序列的树入度和出度时相同的
 *      3、从序列起始处开始遍历,如果遇到数值就入栈
 *          如果遇到"#"号,说明该位置已经到达了叶子节点,这时候需要查看栈顶是否也为"#"
 *          如果是,说明栈顶的前一位置就是该叶子节点的父节点
 *          因此我们将栈顶元素和栈顶的上一元素出栈,然后用"#"替代(入栈,表示某一分支已经结束)
 *          这样操作后,最终根节点的左右孩子都会被替代为"#"，合并后栈中只剩下一个"#"，表明所有节点都已经遍历完
 *          如果最后栈中大小不为1，说明该序列是错误的
 */
class Solution {
    public boolean isValidSerialization1(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return false;
        }
        String[] chrs = preorder.split(",");
        int count = 1; // the root have two exit
        for (String chr : chrs) {
            if (count == 0) {
                return false;
            }
            count += chr.equals("#") ? (-1) : 1;
        }
        return count == 0;
    }

    public boolean isValidSerialization3(String preorder) {
        String[] chrs = preorder.split(",");
        Deque<String> stack = new ArrayDeque<>();
        for (String chr : chrs) {
            // "9,3,4,#,#,1,#,#,2,#,6,#,#"
            if (chr.equals("#")) {
                // clean all unnecessary "#"
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                    // after pop their father
                    // continue to judge the provious element
                    stack.pop();
                }
            }
            // replace their father to #
            stack.push(chr);
        }
        return stack.size() == 1 && stack.pop().equals("#");
    }
}
