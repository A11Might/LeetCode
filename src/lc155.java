import java.util.Stack;

/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 最小栈
 * 
 * 维护两个栈，一个存数据，一个存当前最小值
 *     1>辅助栈 O(n)的空间复杂度(每个当前最小值都放入辅助栈)
 *     2>辅助栈 < O(n)的空间复杂度(当前最小值变化时，放入辅助栈)
 *         只有当data2栈顶元素等于min2栈顶时，min2才pop()，否则只有data2栈pop()
 *         当压入元素小于等于min2栈顶时，min2才会压入当前元素，即当压入元素大于min2栈顶的时，栈的最小值没变，重复使用当前最小值栈的栈顶当最小值
 *         当data2出栈元素为当前的最小值时，最小值栈才一起出栈，否则最小值栈不变
 */
class MinStack {

    private Stack<Integer> datas;
    private Stack<Integer> mins;

    /** initialize your data structure here. */
    public MinStack() {
        datas = new Stack<>();
        mins = new Stack<>();
    }
    
    public void push(int x) {
        if (datas.isEmpty()) {
            mins.push(x);
        } else {
            if (mins.peek() <= x) {
                mins.push(mins.peek());
            } else {
                mins.push(x);
            }
        }
        datas.push(x);  
    }
    
    public void pop() {
        datas.pop();
        mins.pop();
    }

    public void push2(int x) {
        if (datas.isEmpty()) {
            mins.push(x);
        } else if (x <= mins.peek()) {
            mins.push(x);
        }
        datas.push(x);
    }

    public void pop2() {
        int temp = datas.pop();
        if (temp == mins.peek()) {
            mins.pop();
        }
    }

    public int top() {
        return datas.peek();
    }
    
    public int getMin() {
        return mins.peek();
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

