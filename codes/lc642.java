import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode id=1146 lang=java
 *
 * [642] Design Search Autocomplete System
 * 
 * 题目：设计搜索自动补全系统(代码未验证)
 * 
 * 思路：https://www.bilibili.com/video/av66161734
 *       前缀树
 *      
 */
class AutocompleteSystem {
    class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children; // 字符存在路径上，
        String str;
        int times;
        List<TrieNode> hot;

        public TrieNode() {
            children = new TrieNode[128];
            str = null;
            times = 0;
            hot = new ArrayList<>();
        }

        @Override
        public int compareTo(TrieNode o) {
            // 若热度相同，按字典序从小到大排序
            if (this.times == o.times) {
                return this.str.compareTo(o.str);
            }
            // 若热度不同，按热度排序
            return o.times - this.times;
        }

        // 每加入一个新的句子，更新所有经过节点的历史热门
        public void update(TrieNode node) {
            if (!this.hot.contains(node)) {
                this.hot.add(node);
            }

            Collections.sort(hot);
            if (hot.size() > 3) {
                hot.remove(hot.size() - 1);
            }
        }
    }

    TrieNode root;
    TrieNode cur;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        sb = new StringBuilder();

        for (int i = 0; i < times.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    // 在前缀树中加入当前句子和热度，若当前句子出现过，则累计热度
    public void add(String sentence, int time) {
        TrieNode temp = root;
        List<TrieNode> list = new ArrayList<>();
        for (char c : sentence.toCharArray()) {
            if (temp.children[c] == null) {
                temp.children[c] = new TrieNode();
            }
            temp = temp.children[c];
            list.add(temp);
        }

        temp.str = sentence;
        temp.times += time; // 累计热度

        // 每加入一个新的句子，更新所有经过节点的历史热门
        for (TrieNode node : list) {
            node.update(temp);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            add(sb.toString(), 1);
            sb = new StringBuilder();
            cur = root;
            return res;
        }

        sb.append(c);
        // 检索c
        if (cur != null) {
            cur = cur.children[c];
        }
        // 检索不到c
        if (cur == null) {
            return res;
        }
        // 检索到c
        for (TrieNode node : cur.hot) {
            res.add(node.str);
        }

        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

