import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 *
 * 题目：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
 *       (注意空字符串可被认为是有效字符串)
 *
 * 难度：easy
 *
 * 思路：用栈储存左括号，当遇到右括号时，弹出(为离当前右括号最近的左括号)判断是否成对
 */
class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char chr : s.toCharArray()) {
            if (chr == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (chr == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if (chr == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else {
                stack.push(chr);
            }
        }

        return stack.isEmpty(); // 字符全部抵消，则为true(若有剩余说明有不匹配的字符)
    }
}