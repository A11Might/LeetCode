import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU缓存机制
 * 
 * 题目：设计和实现一个LRU(最近最少使用)缓存机制，包括：获取数据get和写入数据put
 * 
 * 思路：哈希表加双向链表
 *      哈希表get()和put()操作O(1)，双向链表维护访问顺序
 */
class LRUCache {

    // doublelinkedlist's node
    class Node {
        int key;
        int value;
        Node pre;
        Node succ;

        public Node() {
            super();
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // doublelinkedlist
    class DoubleLinkedList {
        private Node head;
        private Node tail;

        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();

            head.succ = tail;
            tail.pre = head;
        }

        // Always add the new node right after head
        public void addNode(Node node) {
            node.pre = head;
            node.succ = head.succ;
            head.succ = node;
            node.succ.pre = node;
        }

        // Remove an existing node from the linked list
        public void deleteNode(Node node) {
            Node pre = node.pre;
            Node succ = node.succ;

            pre.succ = succ;
            succ.pre = pre;
        }

        // Move certain node in between to the head
        public void moveToHead(Node node) {
            deleteNode(node);
            addNode(node);
        }

        // Pop the current tail
        public Node popTail() {
            Node res = tail.pre;
            deleteNode(res);

            return res;
        }
    }

    private HashMap<Integer, Node> map;
    private DoubleLinkedList list;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        list = new DoubleLinkedList();
        this.capacity = capacity;
        size = 0;
    }
    
    public int get(int key) {
        Node res = map.get(key);
        // if this key isn't exist, return -1
        if (res == null) {
            return -1;
        // if this key is exist, return the value and change node's priority
        } else {
            list.moveToHead(res);
            return res.value;
        }
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            // if this key isn't exist, create a new node about this key
            node = new Node(key, value);
            map.put(key, node);
            list.addNode(node);
            size++;
            // if size over capacity, remove list's last node
            if (size > capacity) {
                node = list.popTail();
                map.remove(node.value);
            }
        } else {
            // if this key is exist, update the value
            node.value = value;
            list.moveToHead(node);
        }
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

