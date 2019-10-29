import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/29 - 21:54
 *
 * [51] N皇后
 *
 * 题目: 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案
 *
 * 难度: hard
 *
 * 思路: 回溯
 */
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        // 判断不合法情况
        boolean[] col = new boolean[n]; // col[i]表示第i列被占用
        boolean[] dia1 = new boolean[2 * n - 1]; // dia1[i]表示第i对角线1被占用
        boolean[] dia2 = new boolean[2 * n - 1]; // dia2[i]表示第i对角线2被占用
        int[] row = new int[n]; // 每一行放置皇后的位置
        dfs(n, 0, row, col, dia1, dia2, ans);

        return ans;
    }

    private void dfs(int n, int index, int[] row, boolean[] col, boolean[] dia1, boolean[] dia2, List<List<String>> ans) {
        if (index == n) {
            generateBoard(n, row, ans);
            return;
        }
        // 每一行放置一个皇后, 行之间天生不冲突, 只需判断列和对角线即可
        for (int i = 0; i < n; i++) {
            // 标识对角线1: row + col; 标识对角线2: row - col + n - 1(为了让标识从0开始)
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                row[index] = i;
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                dfs(n, index + 1, row, col, dia1, dia2, ans);
                // row[index]每次回溯后覆用, 无序修改
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
            }
        }
    }

    private void generateBoard(int n, int[] row, List<List<String>> ans) {
        List<String> sublist = new ArrayList<>();
        for (int i : row) {
            char[] chrArr = new char[n];
            Arrays.fill(chrArr, '.');
            chrArr[i] = 'Q';
            sublist.add(new String(chrArr));
        }
        ans.add(new ArrayList<>(sublist));
    }
}
