import java.util.HashMap;
import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=149 lang=java
 *
 * [149] 直线上最多的点数
 * 
 * 题目：给定平面上的n个点，求最多有多少个点在同一条直线上
 *
 * 难度：hard
 * 
 * 思路：对于每一个点，求其余各点和这个点组成直线的斜率，斜率相同说明是一条直线，这样可以求出每条直线上的点数，然后取一个最大值
 */
class Solution {
    /**
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return points.length;
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Pair<Integer, Integer>, Integer> record = new HashMap<>();
            int samePoint = 0;
            for (int j = 0; j < n; j++) {
                int dx = points[i][0] - points[j][0], dy = points[i][1] - points[j][1];
                if (dx == 0 && dy == 0) samePoint++;
                else {
                    int g = gcd(dy, dx);
                    if (g != 0) {
                        dy /= g;
                        dx /= g;
                    }
                    Pair<Integer, Integer> curSlope = new Pair(dy, dx); // 用分数表示斜率，防止精度丢失
                    record.put(curSlope, record.getOrDefault(curSlope, 0) + 1);
                }
            }
            res = Math.max(res, samePoint); // 防止全是相同的节点导致record中无元素
            // 更新经过当前i节点的直线上，最多有多少个点
            for (Integer num : record.values()) {
                res = Math.max(res, num + samePoint); // 相同的点包括当前节点i自己
            }
        }

        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}