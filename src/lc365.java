import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/**
 * @author qhhu
 * @date 2020/3/21 - 9:52
 *
 * [365] 水壶问题
 *
 * 题目：有容量为 x 和 y的两个水壶以及无限的水，返回能否得到 z 大小的水。
 *      （允许的操作：装满水壶；清空水壶；从一个水壶向另一个水壶倒水，直到装满或者倒空）
 *
 * 难度：medium
 *
 * 思路：每个时刻的状态可以由 x 水壶和 y 水壶中的水量表示，每个时刻又可以采取的操作把 X 壶的水灌进 Y 壶，直至灌满或倒空；
 *      把 Y 壶的水灌进 X 壶，直至灌满或倒空；把 X 壶灌满；把 Y 壶灌满；把 X 壶倒空；把 Y 壶倒空。所以对问题进行抽象，
 *      将每个时刻抽象成一个节点，每个操作为一条边，构建一个有向图，使用 DFS 判断可达性。
 */
class Solution {
    /**
     * 时间复杂度：O(x * y)
     * 空间复杂度：O(x * y)
     */
    public boolean canMeasureWater(int x, int y, int z) {
        Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        stack.push(new Pair<>(0, 0));
        while (!stack.isEmpty()) {
            Pair<Integer, Integer> cur = stack.pop();
            if (visited.contains(cur)) continue;
            visited.add(cur);
            int remainX = cur.getKey(), remainY = cur.getValue();
            // 可以得到 z 大小的水。
            if (remainX == z || remainY == z || remainX + remainY == z) return true;
            // 将 x 水壶装满。
            stack.push(new Pair<>(x, remainY));
            // 将 y 水壶装满。
            stack.push(new Pair<>(remainX, y));
            // 将 x 水壶倒空。
            stack.push(new Pair<>(0, remainY));
            // 将 y 水壶倒空。
            stack.push(new Pair<>(remainX, 0));
            // 将 x 水壶的水倒入 y 水壶。
            stack.push(new Pair<>(remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)));
            // 将 y 水壶的水倒入 x 水壶。
            stack.push(new Pair<>(remainX + Math.min(remainY, x - remainX), remainY - Math.min(remainY, x - remainX)));
        }

        return false;
    }
}