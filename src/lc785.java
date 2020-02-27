import java.util.Arrays;
import java.util.Stack;

/**
 * @author qhhu
 * @date 2020/2/26 - 10:05
 *
 * [785] 判断二分图
 *
 * 题目: 给定一个无向图 graph, 当这个图为二分图时返回 true.
 *
 * 难度: medium
 *
 * 思路: 深度优先搜索着色, 搜索节点时, 需要考虑图是非连通的情况. 所以需要从每个未着色的节点开始深度优先搜索着色, 将所有相邻的节点
 *      染上与当前节点着相反的颜色. 当当前点和邻接点颜色相同时, 则着色失败, 即该图不是二分图.
 */
class Solution {
    /**
     * 时间复杂度: O(n + e) (n 为节点数, e 为边数)
     * 空间复杂度: O(n)
     */
    public boolean isBipartite1(int[][] graph) {
        int size = graph.length;
        // -1 代表没有访问过; 0 代表一种颜色; 1 代表另一种颜色.
        int[] colors = new int[size];
        Arrays.fill(colors, -1);
        // 从每个节点出发遍历, 用来处理图不是连通的情况.
        for (int i = 0; i < size; i++) {
            if (colors[i] == -1 && !isisBipartiteCore(graph, colors, i, 0)) {
                return false;
            }
        }
        return true;
    }

    // 使用dfs着色该图, 判断该图是否为为二分图.
    private boolean isisBipartiteCore(int[][] graph, int[] colors, int curNode, int curColor) {
        if (colors[curNode] != -1) {
            return colors[curNode] == curColor;
        }
        colors[curNode] = curColor;
        for (int nextNode : graph[curNode]) {
            if (!isisBipartiteCore(graph, colors, nextNode, 1 - curColor)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时间复杂度: O(n + e) (n 为节点数, e 为边数)
     * 空间复杂度: O(n)
     */
    public boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; start++) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == -1) {
                            stack.push(nei);
                            color[nei] = color[node] ^ 1;
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}