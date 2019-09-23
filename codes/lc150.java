import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=150 lang=java
 *
 * [150] 逆波兰表达式求值
 *
 * 题目：根据逆波兰表示法，求表达式的值
 *      (有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式)
 *      (整数除法只保留整数部分；给定逆波兰表达式总是有效的)
 *
 * 难度：medium
 *
 * 思路：将数字压入栈中，遇到操作符时，弹出两个数字运算
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (str.equals("-")) {
                int succ = stack.pop();
                stack.push(stack.pop() - succ);
            } else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (str.equals("/")) {
                int succ = stack.pop();
                stack.push(stack.pop() / succ);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }

        return stack.pop();
    }
}
