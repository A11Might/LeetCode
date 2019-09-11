import java.util.HashMap;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=149 lang=java
 *
 * [149] 直线上最多的点数
 * 
 * 题目：给定平面上的n个点，求最多有多少个点在同一条直线上
 * 
 * 思路：查找表map，对于每一个点，求其余各点和这个点组成直线的斜率，斜率相同说明是一条直线
 */
class Solution {
    public int maxPoints(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Pair<Integer, Integer>, Integer> record = new HashMap<>();
            int samePoint = 0;
            for (int j = 0; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    samePoint++;
                } else {
                    Pair<Integer, Integer> curSlope = slope(points[i], points[j]); // 用最大约数方法(gcd)表示斜率，防止精度丢失
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

    // 用最大约数方法(gcd)表示斜率
    private Pair<Integer, Integer> slope(int[] pa, int[] pb) {
        int dy = pa[1] - pb[1];
        int dx = pa[0] - pb[0];
        if (dx == 0) {
            return new Pair<>(1, 0);
        }
        if (dy == 0) {
            return new Pair<>(0, 1);
        }
        int g = gcd(Math.abs(dy), Math.abs(dx));
        dy /= g;
        dx /= g;
        if (dx < 0) {
            dy = -dy;
            dx = -dx;
        }

        return new Pair<>(dy, dx);
    }

    private int gcd(int a, int b) {
        if (a < b) {
            int temp = b;
            b = a;
            a = temp;
        }
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }
}

