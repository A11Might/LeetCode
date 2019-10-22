/*
 * @lc app=leetcode.cn id=208 lang=java
 *
 * [208] 实现 Trie (前缀树)
 * 
 * 题目：实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作(所有输入均为非空字符串)
 * 
 * 思路：节点上保存次数，路径上保存字符(方便实现)
 *      如ab：root(0, 0, nexts[a - 'a']) --> (1, 0, nexts[b - 'b']) --> (1, 1, null);
 */
class Trie {
    public class TrieNode {
        public int path; // 经过该节点的字符串个数
        public int end; // 依该节点结尾的字符串个数
        public TrieNode[] nexts; // 表示路径(所有的输入都是由小写字母 a-z 构成的)

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chrs = word.toCharArray();
        TrieNode cur = root;
        for (char chr : chrs) {
            int index = chr - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode();
            }
            cur = cur.nexts[index];
            cur.path++;
        }
        cur.end++;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chrs = word.toCharArray();
        TrieNode cur = root;
        for (char chr : chrs) {
            int index = chr - 'a';
            if (cur.nexts[index] == null) {
                return false;
            }
            cur = cur.nexts[index];
        }
        return cur.end >= 1;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chrs = prefix.toCharArray();
        TrieNode cur = root;
        for (char chr : chrs) {
            int index = chr - 'a';
            if (cur.nexts[index] == null) {
                return false;
            }
            cur = cur.nexts[index];
        }
        return cur.path >= cur.end; // ab为ab的前缀
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

