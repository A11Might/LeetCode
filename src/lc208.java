/*
 * @lc app=leetcode.cn id=208 lang=java
 *
 * [208] 实现 Trie (前缀树)
 * 
 * 题目：实现一个 Trie （前缀树），包含 insert, search 和 startsWith 这三个操作
 *      （所有输入都是由小写字母 a - z 组成；所有输入均为非空字符串）
 *
 * 难度：medium
 *
 * 思路：将字符保存在路径上，因为字符集只有小写字母，所以使用大小为 26 的布尔数组即可保存所有种类字符。
 */
class Trie {

    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char chr : word.toCharArray()) {
            int idx = chr - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new TrieNode();
            cur = cur.next[idx];
        }
        cur.end = true;
    }
    
    /** Returns if the word is in the trie. */
    // 按照插入的方式进行查找(end == true)
    public boolean search(String word) {
        TrieNode cur = root;
        for (char chr : word.toCharArray()) {
            int idx = chr - 'a';
            if (cur.next[idx] == null) return false;
            cur = cur.next[idx];
        }
        return cur.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    // 同查找, 只要前缀树中包含前缀即可(不需要end == true)
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char chr : prefix.toCharArray()) {
            int idx = chr - 'a';
            if (cur.next[idx] == null) return false;
            cur = cur.next[idx];
        }
        return true;
    }
    
    private class TrieNode {
        TrieNode[] next;
        boolean end;
        
        public TrieNode() {
            next = new TrieNode[26];
            end = false;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */