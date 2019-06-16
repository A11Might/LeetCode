/*
 * @lc app=leetcode.cn id=79 lang=java
 *
 * [79] 单词搜索
 * 
 * 题意：在给定的网格中按字母顺序查找单词，网格中的字母不能重复使用
 * 
 * 思路：回溯
 * 
 * Tips：递归只看两步，这一步和下一步，在下一步已解决的情况下，考虑怎么解决这一步
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        // 遍历整个矩阵，当遇到与单词首字母相同的格子时，开始回溯
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == word.charAt(0) && process(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    // 回溯从当前格子开始是否能找到单词
    private boolean process(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length 
        || board[i][j] != word.charAt(index) || visited[i][j] == true) {
            return false;
        }
        // 从四个方向找
        visited[i][j] = true;
        if (process(board, word, index + 1, i + 1, j, visited) 
        || process(board, word, index + 1, i, j + 1, visited)
        || process(board, word, index + 1, i - 1, j, visited)
        || process(board, word, index + 1, i, j - 1, visited)) {
            return true;
        }
        visited[i][j] = false; // 还原visited矩阵，防止对exist函数下一次调用回溯操作产生影响 <-----我真的干了
        return false;
    }
}

