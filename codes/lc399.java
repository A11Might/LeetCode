import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=399 lang=java
 *
 * [399] 除法求值
 * 
 * 题目：给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字
 * 根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 * 
 * 思路：根据等式及其值建立有向图，将除法求值抽象成深度优先遍历
 * a / b = 2.0，则建立a节点到b节点的权重为2.0，b节点到a节点的权重1 / 2.0
 *              a到a为1.0，b到b为1.0
 */
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> g = new HashMap<>();
        buildGraph(g, equations, values); // 根据等式及其值建立有向图

        double[] res = new double[queries.size()];
        Arrays.fill(res, -1.0);

        int index = 0; // 标记当前是第几个querie
        for (List<String> querie : queries) {
            String a = querie.get(0);
            String b = querie.get(1);

            // 若图中不存在a或b，则返回-1.0(由于设置了返回值初值为-1.0，所以不用操作)
            if (!g.containsKey(a) || !g.containsKey(b)) {
                index++;
                // 正常情况，深度优先遍历
            } else {
                dfs(g, a, b, res, index, 1.0, new HashSet<>());
                index++;
            }
        }

        return res;
    }

    /**
     * 深度优先遍历
     * 
     * @param temp 到当前节点为止，过程中计算的值
     */
    private void dfs(Map<String, Map<String, Double>> g, String a, String b, double[] res, int index, double temp,
            Set<String> visited) {
        visited.add(a); // 注册当前起点节点，防止循环访问导致栈溢出

        // 无法到达目标节点，则直接返回(由于设置了返回值初值为-1.0，所以不用操作)
        if (g.get(a) == null || g.get(a).size() == 0) {
            return;
        }

        // 当前起点节点与目标节点相连
        if (g.get(a).containsKey(b)) {
            res[index] = temp * g.get(a).get(b);
            return;
        }

        // 当前起点节点与目标节点不直接相连
        for (String next : g.get(a).keySet()) {
            if (visited.contains(next))
                continue;
            dfs(g, next, b, res, index, temp * g.get(a).get(next), visited);
        }
    }

    // 用等式及其值建立有向图
    private void buildGraph(Map<String, Map<String, Double>> g, List<List<String>> equations, double[] values) {
        int index = 0; // 标记当前是第几个equation
        for (List<String> equation : equations) {
            String a = equation.get(0); // 等式的分子
            String b = equation.get(1); // 等式的分母
            g.putIfAbsent(a, new HashMap<>()); // 建立a节点
            g.putIfAbsent(b, new HashMap<>()); // 建立b节点
            g.get(a).put(b, values[index]); // 建立a -> b
            g.get(a).put(a, 1.0); // 建立a -> a，用于计算a / a
            g.get(b).put(a, 1.0 / values[index]); // 建立b -> a
            g.get(b).put(b, 1.0); // 建立b -> b
            index++;
        }
    }
}
