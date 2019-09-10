import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=454 lang=java
 *
 * [454] 四数相加 II
 * 
 * 题目：给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
 * 
 * 思路：将A + B和C + D的每一种可能放入两个查找表中
 */
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> mapAB = new HashMap<>(); // <A[i] + B[j], times>
        HashMap<Integer, Integer> mapCD = new HashMap<>(); // <C[i] + D[j], times>
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                mapAB.put(A[i] + B[j], mapAB.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                mapCD.put(C[i] + D[j], mapCD.getOrDefault(C[i] + D[j], 0) + 1);
            }
        }

        // 找到所有A[i] + B[j] + C[k] + D[l] = 0的情况
        int res = 0;
        for (Integer sumAB : mapAB.keySet()) {
            if (mapCD.containsKey(-sumAB)) {
                res += mapAB.get(sumAB) * mapCD.get(-sumAB);
            }
        }

        return res;
    }
}

