/**
 * @author qhhu
 * @date 2020/1/29 - 11:32
 *
 * [547] 朋友圈
 *
 * 题目: 有n个人, 若a和b是朋友, b和c是朋友, 那么a和c也是朋友. 所谓朋友圈就是所有朋友的集合
 *      给定一个n * n的矩阵m来表示这n个人的朋友关系. 若m[i][j] = 1, 表示已知第i个人和第j个人互为朋友关系, 返回已知朋友圈的总数
 *
 * 难度: medium
 *
 * 思路: dfs, 将这n个人的朋友圈看成无权图, 则给定的n * n的矩阵就是这张图的邻接矩阵, 遍历一个人, dfs其所有朋友并标记这样就得到了一个朋友圈;
 *           再遍历另一个未标记的人, dfs其所有朋友这样又得到一个朋友圈, 以此类推.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    private int n;
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        n = M.length;
        boolean[] visited = new boolean[n]; // 标记当前节点是否访问过
        int cnt = 0; // 当前的朋友圈数
        for (int i = 0; i < n; i++) {
            // 从未标记的节点开始深度优先搜索
            if (visited[i]) continue;
            cnt++; // 朋友圈数加一
            dfs(M, visited, i);
        }

        return cnt;
    }

    // 深度优先搜索当前节点所有朋友并标记
    private void dfs(int[][] matrix, boolean[]visited, int i) {
        if (visited[i]) return;
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 1) {
                dfs(matrix, visited, j);
            }
        }
    }
}