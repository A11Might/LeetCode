/**
 * [212] 单词搜索 II
 * 
 * 题目：在给定二维网格 board 中查找单词列表 words 中出现的单词
 * 
 * 难度：hard
 * 
 * 思路：dfs 暴力搜索出所有单词，再判断该单词是否在单词列表中出现。
 *      剪枝，先将所有单词存入前缀树中，这样当我们搜索到的当前单词前缀不在前缀树中，那么当前单词前缀一定不会构成任意一个单词列表中的单词
 */
class Solution {
    /**
     * 时间复杂度：O()
     * 空间复杂度：O()
     */
    private TrieNode root = new TrieNode();
    private HashSet<String> hash = new HashSet<>();
    private List<String> ans = new ArrayList<>();
    private int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0) return Collections.emptyList();
        // 通过单词列表构建前缀树，来快速判断查找单词是否在单词列表中
        for (int i = 0; i < words.length; i++) insert(words[i], i);
        int n = board.length, m = board[0].length;
        boolean[][] st = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(board, st, i, j, root.next[board[i][j] - 'a'], words);
            }
        }
        return ans;
    }
    
    private void dfs(char[][] board, boolean[][] st, int i, int j, TrieNode cur, String[] words) {
        if (cur == null) return;
        st[i][j] = true;
        if (cur.id != -1) {
            String match = words[cur.id];
            if (!hash.contains(match)) {
                hash.add(match);
                ans.add(match);
            }
        }
        for (int[] d : dir) {
            int x = i + d[0], y = j + d[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length 
                || st[x][y]) continue;
            dfs(board, st, x, y, cur.next[board[x][y] - 'a'], words);
        }
        st[i][j] = false;
    }
    
    private class TrieNode {
        int id;
        TrieNode[] next;
        
        public TrieNode() {
            id = -1;
            next = new TrieNode[26];
        }
    }
    
    private void insert(String word, int id) {
        TrieNode cur = root;
        for (char chr : word.toCharArray()) {
            int idx = chr - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new TrieNode();
            cur = cur.next[idx];
        }
        cur.id = id;
    }
}