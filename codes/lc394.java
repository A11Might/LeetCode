import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=394 lang=java
 *
 * [394] 字符串解码
 * 
 * 题意：编码规则为:k[encoded_string]，表示其中方括号内部的encoded_string正好重复k次，返回它解码后的字符串
 * 
 * https://www.bilibili.com/video/av54757532
 * 思路：准备两个辅助栈和一个StringBuilder，依次遍历字符串的每个字符，
 * 1.遇到数字压入数字栈中
 * 2.遇到字符添加进StringBuilder
 * 3.遇到'['说明当前StringBuilder不着急重复，其应在其后'[]'中的字符重复完后加在其后再进行重复，所以先将其压入字符串栈，重置StringBuilder
 * 4.遇到']'说明应该重复当前StringBuilder，重复完后加上'[]'之前第一个待重复元素(步骤3压入栈中的元素)，继续作为当前遍历到的该重复的字符
 */
class Solution {
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        StringBuilder tail = new StringBuilder(); // 当前遍历到的该重复的字符

        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                numStack.push(num);
            } else if (c == '[') {
                strStack.push(tail.toString());
                tail = new StringBuilder();
            } else if (c == ']') {
                int repeatTimes = numStack.pop();
                // strStack不会弹出空，因为每个闭括号对应一个开括号，遇到开括号时strString一定会加入一个字符串(当前遍历到的该重复的字符或空字符串)
                StringBuilder temp = new StringBuilder(strStack.pop());
                for (int j = 0; j < repeatTimes; j++) {
                    temp.append(tail);
                }
                tail = temp;
            } else {
                tail.append(c);
            }
        }
        return tail.toString();
    }
}

