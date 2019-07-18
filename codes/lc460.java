/*
 * @lc app=leetcode.cn id=460 lang=java
 *
 * [460] LFU缓存
 * 
 * 题目：设计并实现最不经常使用缓存数据结构，支持get和put操作
 * 
 * 思路：二维链表结构
 */
class LFUCache {
    private int capacity;
    private int size;
    private HashMap<Integer, Node> records;
    private HashMap<Node, NodeList> heads;
    private NodeList headList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.records = new HashMap<>();
        this.heads = new HashMap<>();
        headList = null;
    }

    public void put(int key, int value) {
        if (capacity == 0) { // ["LFUCache","put","get"]
            return;          // [[0],[0,0],[0]]
        }
        if (records.containsKey(key)) {
            Node node = records.get(key);
            node.value = value;
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList);
        } else {
            if (size == capacity) {
                Node node = headList.tail;
                headList.deleteNode(node);
                modifyHeadList(headList);
                records.remove(node.key);
                heads.remove(node);
                size--;
            }
            Node node = new Node(key, value, 1);
            if (headList == null) {
                headList = new NodeList(node);
            } else {
                if (headList.head.times.equals(node.times)) {
                    headList.addNodeFromHead(node);
                } else {
                    NodeList newList = new NodeList(node);
                    newList.next = headList;
                    headList.last = newList;
                    headList = newList;
                }
            }
            records.put(key, node);
            heads.put(node, headList);
            size++;
        }
    }

    // 将当前节点从当前链表中移动下一个链表中
    private void move(Node node, NodeList oldNodeList) {
        oldNodeList.deleteNode(node);
        NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last : oldNodeList;
        NodeList nextList = oldNodeList.next;
        if (nextList == null) {
            NodeList newList = new NodeList(node);
            if (preList != null) {
                preList.next = newList;
            }
            newList.last = preList;
            if (headList == null) {
                headList = newList;
            }
            heads.put(node, newList);
        } else {
            if (nextList.head.times.equals(node.times)) {
                nextList.addNodeFromHead(node);
                heads.put(node, nextList);
            } else {
                NodeList newList = new NodeList(node);
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                newList.next = nextList;
                nextList.last = newList;
                if (headList == nextList) {
                    headList = newList;
                }
                heads.put(node, newList);
            }
        }
    }

    // 返回是否删除当前链表
    private boolean modifyHeadList(NodeList nodeList) {
        if (nodeList.isEmpty()) {
            if (headList == nodeList) {
                headList = nodeList.next;
                if (headList != null) {
                    headList.last = null;
                }
            } else {
                nodeList.last.next = nodeList.next;
                if (nodeList.next != null) {
                    nodeList.next.last = nodeList.last;
                }
            }
            return true;
        }
        return false;
    }

    public int get(int key) {
        if (!records.containsKey(key)) {
            return -1;
        }
        Node node = records.get(key);
        node.times++;
        NodeList curNodeList = heads.get(node);
        move(node, curNodeList);
        return node.value;
    }
}

// 定制的节点类
class Node {
    public Integer key;
    public Integer value;
    public Integer times; // 节点使用次数
    public Node up; // 上一个节点
    public Node down; // 下一个节点

    public Node(int key, int value, int times) {
        this.key = key;
        this.value = value;
        this.times = times;
    }
}

// 定制的二维链表类
class NodeList {
    public Node head; // 链表头节点
    public Node tail; // 链表尾节点
    public NodeList last; // 上一个链表
    public NodeList next; // 下一个链表

    public NodeList(Node node) {
        head = node;
        tail = node;
    }

    // 在链表的头部添加节点
    public void addNodeFromHead(Node newHead) {
        newHead.down = head;
        head.up = newHead;
        head = newHead;
    }

    // 是否为空
    public boolean isEmpty() {
        return head == null;
    }

    // 删除任意节点
    public void deleteNode(Node node) {
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            if (node == head) {
                head = node.down;
                head.up = null;
            } else if (node == tail) {
                tail = node.up;
                tail.down = null;
            } else {
                node.up.down = node.down;
                node.down.up = node.up;
            }
        }
        node.up = null;
        node.down = null;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

