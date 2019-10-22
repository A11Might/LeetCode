import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=447 lang=java
 *
 * [447] 回旋镖的数量
 * 
 * 题目：在给定的n个不同的点中，找到所有i到j的距离和i到k的距离相同的三元组[i, j, k]的个数
 *       （需要考虑元组的顺序）
 * 
 * 思路：查找表map，i是一个枢纽，对于每个i点，遍历其余点到i的距离，找到所有符合条件的三元组个数
 */
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if (points.length < 3) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>(); // <到点i的距离dis，到点i距离为dis的点的个数>
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                int[] I = points[i];
                int[] cur = points[j];
                int dis = (int) (Math.pow(I[0] - cur[0], 2) + Math.pow(I[1] - cur[1], 2)); // 不开方，防止精度丢失
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            // 统计以当前点为i点，所有i到j的距离和i到k的距离相同的三元组的个数
            for (int num : map.values()) {
                if (num > 1) {
                    res += num * (num - 1);
                }
            }
        }

        return res;
    }
}

