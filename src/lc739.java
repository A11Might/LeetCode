import java.util.Stack;

/*
 * @lc app=leetcode.cn id=739 lang=java
 *
 * [739] 每日温度
 * 
 * 题目：根据每日气温列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数
 *       如果之后都不会升高，请在该位置用 0 来代替
 * 
 * 思路：使用单调栈
 */
class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; ++i) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int biggerIndex = stack.pop();
                res[biggerIndex] = i - biggerIndex;
            }
            stack.push(i);
        }
        return res;
    }
}

