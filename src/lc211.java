/**
 * [211] 添加与搜索单词 - 数据结构设计
 * 
 * 题目：设计一个支持 addWord(word), search(word) 的数据结构。
 *       （search(word) 可以搜索文字和正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。）
 * 
 * 难度：medium
 * 
 * 思路：使用前缀树保存所有单词，插入单词时直接插入；
 *       搜索单词时因为包含通配符 .，所以可以使用 dfs
 *          - 当遇到字母 a - z 时，直接进入前缀树节点对应的儿子
 *          - 当遇到通配符 .时，枚举当前前缀树节点的所有儿子
 */
class WordDictionary {

    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char chr : word.toCharArray()) {
            int idx = chr - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new TrieNode();
            cur = cur.next[idx];
        }
        cur.end = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }
    
    private boolean dfs(String word, int idx, TrieNode cur) {
        if (idx == word.length()) return cur.end;
        char chr = word.charAt(idx);
        if (chr != '.') {
            // 当遇到字母 a - z 时，直接进入 trie 树节点对应的儿子
            if (cur.next[chr - 'a'] != null) {
                return dfs(word, idx + 1, cur.next[chr - 'a']);
            }
        } else {
            // 当遇到通配符 . 时，枚举当前 trie 树节点的所有儿子
            for (int i = 0; i < 26; i++) {
                if (cur.next[i] != null) {
                    if (dfs(word, idx + 1, cur.next[i])) return true;
                }
            }
        }
        return false;
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
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */