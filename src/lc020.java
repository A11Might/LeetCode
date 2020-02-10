import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 *
 * 题目: 给定一个只包括 '(', ')', '{', '}', '[', ']' 的字符串, 判断字符串是否有效
 *       (字符串有效需满足: 左括号必须用相同类型的右括号闭合; 左括号必须以正确的顺序闭合.
 *        注意空字符串可被认为是有效字符串)
 *
 * 难度: easy
 *
 * 思路: 遍历字符串, 用栈储存当前遍历到的字符, 当遇到右括号时, 弹出(为离当前右括号最近的左括号)判断是否成对
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public boolean isValid(String s) {
        if (s.length() == 0) return true;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{') {
                stack.push(cur);
            } else {
                if (stack.isEmpty()) return false;
                char pre = stack.pop();
                if (cur == ')') {
                    if (pre != '(') return false;
                } else if (cur == ']') {
                    if (pre != '[') return false;
                } else if (cur == '}') {
                    if (pre != '{') return false;
                }
            }
        }

        return stack.isEmpty(); // 字符全部抵消, 则为true(若有剩余说明有不匹配的字符)
    }
}