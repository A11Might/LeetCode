/**
 * @author qhhu
 * @date 2019/10/30 - 19:54
 *
 * [52] N皇后 II
 *
 * 题目: 给定一个整数 n，返回 n 皇后不同的解决方案的数量
 *
 * 难度: hard
 *
 * 思路: 回溯
 *      (使用 bitmap 回溯可以看一看: https://leetcode-cn.com/problems/n-queens-ii/solution/nhuang-hou-ii-by-leetcode/)
 */
class Solution {
    public int totalNQueens(int n) {
        int[] ans = {0}; // n 皇后不同的解决方案的数量
        // 判断不合法情况
        boolean[] col = new boolean[n]; // col[i]表示第i列被占用
        boolean[] dia1 = new boolean[2 * n - 1]; // dia1[i]表示第i对角线1被占用
        boolean[] dia2 = new boolean[2 * n - 1]; // dia2[i]表示第i对角线2被占用
        dfs(n, 0, col, dia1, dia2, ans);

        return ans[0];
    }

    private void dfs(int n, int index, boolean[] col, boolean[] dia1, boolean[] dia2, int[] ans) {
        if (index == n) {
            ans[0]++;
            return;
        }
        // 每一行放置一个皇后, 行之间天生不冲突, 只需判断列和对角线即可
        for (int i = 0; i < n; i++) {
            // 标识对角线1: row + col; 标识对角线2: row - col + n - 1(为了让标识从0开始)
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                dfs(n, index + 1, col, dia1, dia2, ans);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
            }
        }
    }
}
