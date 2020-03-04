import java.util.ArrayDeque;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=225 lang=java
 *
 * [225] 用队列实现栈
 *
 * 题目: 使用队列实现栈的push(x), pop(), top()和empty()操作
 *
 * 难度: easy
 *
 * 思路: 在将一个元素x插入队列时, 为了维护原来的后进先出顺序, 需要让x插入队列首部. 而队列的默认插入顺序是队列尾部, 因此在将x插入队列尾部之后,
 *      需要让除了x之外的所有元素出队列, 再入队列.
 *      使用一个队列, push: 加入data队列后, 调整队列元素出队顺序为栈弹出顺序
 *                  pop: 直接出队队首元素
 *                  peek: 同弹出
 *                  empty: data为空时, 栈为中无数据
 */
class MyStack {
    private Queue<Integer> data;

    /** Initialize your data structure here. */
    public MyStack() {
        data = new ArrayDeque<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        data.add(x);
        int size = data.size();
        while (size-- > 1) {
            data.add(data.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return data.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return data.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return data.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

