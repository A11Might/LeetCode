import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU缓存机制
 * 
 * 题目：设计和实现一个LRU（最近最少使用）缓存机制，包括：获取数据 get 和写入数据 put
 *
 * 难度：medium
 * 
 * 思路：哈希表加双向链表，哈希表 get() 和 put() 操作 O(1)，双向链表维护访问顺序
 */
class LRUCache {

    private int capacity, size;
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head = new Node(), tail = new Node();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node cur = map.get(key);
        // if this key isn't exist, return -1
        if (cur == null) return -1;
        else {
            // if this key is exist, return the value and change node's priority
            moveToFirst(cur);
            return cur.val;
        }
    }

    public void put(int key, int value) {
        Node cur = map.get(key);
        if (cur != null) {
            // if this key is exist, update the value, and move item after head
            cur.val = value;
            moveToFirst(cur);
        } else {
            // if this key isn't exist, create a new node about this key, and add item after head
            Node node = new Node(key, value);
            map.put(key, node);
            addFirst(node);
            size++;
            // if size over capacity, remove list's last node
            if (size > capacity) {
                Node last = pollLast();
                map.remove(last.key);
            }
        }
    }

    //  add node after head
    private void addFirst(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next = node;
        node.next.pre = node;
    }

    // remove node from the linked list
    private void deleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    // move certain node to the head's right
    private void moveToFirst(Node node) {
        deleteNode(node);
        addFirst(node);
    }

    // pop the current tail node
    private Node pollLast() {
        Node ret = tail.pre;
        deleteNode(ret);
        return ret;
    }

    private class Node {
        int key, val;
        Node pre, next;

        public Node() {}

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */