import java.util.Stack;

/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 最小栈
 *
 * 题目：设计一个支持 push，pop 和 top 操作，并能在常数时间内检索到最小元素的栈
 *
 * 难度：easy
 *
 * 思路：维护两个栈，一个存数据，一个存当前最小值
 *                1. 辅助栈 O(n) 的空间复杂度（每个当前最小值都压入辅助栈中）
 *                   push：当前元素压入 data 栈中，并将当前最小值压入 min 栈中
 *                   pop：同时弹出 data 和 min 的栈顶元素
 *                2. 辅助栈 < O(n) 的空间复杂度（当前值小于等于之前最小值时压入辅助栈中）
 *                   push：当压入元素小于等于 min 栈顶时，min 栈才会压入当前元素，即当压入元素大于 min 栈顶的时，栈的最小值没变，重复使用当前最小值栈的栈顶当最小值
 *                   pop：只有当 data 栈顶元素等于 min 栈顶时，min 栈才 pop()，否则只有 data 栈 pop()
 */
class MinStack {
    private Stack<Integer> data;
    private Stack<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if (min.isEmpty() || x < min.peek()) min.push(x);
        else min.push(min.peek());
    }

    public void pop() {
        data.pop();
        min.pop();
    }

    public void push2(int x) {
        data.push(x);
        // 当当前值小于等于之前最小值时，将当前值压入最小值栈
        // 需要等于因为在弹出时，弹出时数据栈中与最小值相同的元素需要同时弹出
        // 若之前等于时不将元素压入最小值栈，这时弹出的话，data 剩余元素的最小值就出错了（在这时弹出了）
        if (min.isEmpty() || x <= min.peek()) min.push(x);
    }

    public void pop2() {
        int cur = data.pop();
        if (cur == min.peek()) min.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

