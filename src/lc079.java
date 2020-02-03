/*
 * @lc app=leetcode.cn id=79 lang=java
 *
 * [79] 单词搜索
 * 
 * 题意: 在给定的网格中按字母顺序查找单词, 网格中的字母不能重复使用
 *
 * 难度: medium
 * 
 * 思路: 回溯(剪枝, 看当前字母是否对应Word中的字母(我傻乎乎的搞了个curStr))
 * 
 * Tips: 递归只看两步, 这一步和下一步, 在下一步已解决的情况下, 考虑怎么解决这一步
 */
class Solution {
    /**
     * 时间复杂度: O((m * n) ^2)
     * 空间复杂度: O(m * n)
     */
    private int rows, cols, len;
    private int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        rows = board.length;
        cols = board[0].length;
        len = word.length();
        boolean[][] visited = new boolean[rows][cols];
        // 遍历整个矩阵, 当遇到与单词首字母相同的格子时, 开始dfs
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, visited, i, j, 0)) return true;
                }
            }
        }

        return false;
    }

    // dfs从当前格子开始是否能找到单词
    private boolean dfs(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
        if (index == word.length()) return true;
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                board[i][j] != word.charAt(index) ||  visited[i][j]) return false;
        visited[i][j] = true;
        // 向四个方向寻找
        for (int[] d : direction) {
            if (dfs(board, word, visited, i + d[0], j + d[1], index + 1)) return true;
        }
        visited[i][j] = false; // 回溯, 还原visited矩阵, 防止对exist函数下一次调用回溯操作产生影响 <-----我真的干了

        return false;
    }
}

