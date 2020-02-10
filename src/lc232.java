import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=232 lang=java
 *
 * [232] 用栈实现队列
 *
 * 题目: 使用栈实现队列的push(x), pop(), peek()和empty()操作
 *
 * 难度: easy
 * 
 * 思路: 栈的顺序为后进先出, 而队列的顺序为先进先出. 使用两个栈实现队列, 一个元素需要经过两个栈才能出队列, 在经过第一个栈时元素顺序被反转,
 *      经过第二个栈时再次被反转, 此时就是先进先出顺序.
 *      用两个栈, push: 入队时直接压入data栈
 *               pop: 出队时将data中的数据倒入help, 弹出help的栈顶
 *               peek: 同弹出
 *               empty: 当data和help都为空时, 队列中无数据
 */
class MyQueue {
    // data和help都可能含有数据
    private Deque<Integer> data;
    private Deque<Integer> help;

    /** Initialize your data structure here. */
    public MyQueue() {
        data = new ArrayDeque<>();
        help  = new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        data.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // help为空时才能将data中元素倒入help
        if (help.isEmpty()) {
            while (!data.isEmpty()) {
                help.push(data.pop());
            }
        }

        return help.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        // help为空时才能将data中元素倒入help
        if (help.isEmpty()) {
            while (!data.isEmpty()) {
                help.push(data.pop());
            }
        }

        return help.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return data.isEmpty() && help.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

