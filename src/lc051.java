import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/29 - 21:54
 *
 * [51] N皇后
 *
 * 题目: 给定一个整数 n, 返回所有不同的 n 皇后问题的解决方案
 *
 * 难度: hard
 *
 * 思路: 回溯, 一行一行地摆放, 在确定一行中的那个皇后应该摆在哪一列时, 需要用三个标记数组来确定某一列是否合法
 *           这三个标记数组分别为: 列标记数组, 45 度对角线标记数组和 135 度对角线标记数组
 */
class Solution {
    /**
     * 时间复杂度: O(n!)
     * 空间复杂度: O(n)
     */
    private boolean[] colsUsed; // col[i]表示第i列被占用
    private boolean[] diagonalsUsed1; // dia1[i]表示第i对角线1被占用
    private boolean[] diagonalsUsed2; // dia2[i]表示第i对角线2被占用
    List<List<String>> ans = new ArrayList<>();
    int[] rowPositions; // 每一行放置皇后的位置
    public List<List<String>> solveNQueens(int n) {
        colsUsed = new boolean[n];
        diagonalsUsed1 = new boolean[2 * n - 1];
        diagonalsUsed2 = new boolean[2 * n - 1];
        rowPositions = new int[n];
        dfs(n, 0);

        return ans;
    }

    private void dfs(int n, int index) {
        if (index == n) {
            generateBoard(n);
            return;
        }
        // 分别尝试在在当前行index的0-8位置上放置皇后
        for (int i = 0; i < n; i++) {
            // 关键:
            // 标记对角线上的皇后位置(../image/solution/lc051.png)
            // 标识对角线1: row + col; 标识对角线2: row - col + n - 1(为了让标识从0开始)
            // 每一行放置一个皇后, 行之间天生不冲突, 只需判断列和对角线即可
            if (colsUsed[i] || diagonalsUsed1[index + i] || diagonalsUsed2[index - i + n - 1]) continue;
            // 记录当前行皇后放置的位置
            rowPositions[index] = i;
            colsUsed[i] = diagonalsUsed1[index + i] = diagonalsUsed2[index - i + n - 1] = true;
            dfs(n, index + 1);
            // row[index]每次回溯后覆用, 无需修改
            colsUsed[i] = diagonalsUsed1[index + i] = diagonalsUsed2[index - i + n - 1] = false; // 回溯
        }
    }

    // 根据每一行放置皇后的位置, 还原出每种解法
    private void generateBoard(int n) {
        List<String> sublist = new ArrayList<>();
        for (int i : rowPositions) {
            char[] chrs = new char[n];
            Arrays.fill(chrs, '.');
            chrs[i] = 'Q';
            sublist.add(new String(chrs));
        }
        ans.add(new ArrayList(sublist));
    }
}