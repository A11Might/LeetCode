import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=118 lang=java
 *
 * [118] 杨辉三角
 * 
 * 根据每对相邻的值计算出它的下一行
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        // 加入第一行
        res.add(new ArrayList<>());
        res.get(0).add(1);
        for (int i = 1; i < numRows; ++i) {
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = res.get(i - 1);
            row.add(1);
            // g(n) = f(n - 1) + f(n) 下一行等于上一行相邻两数之和
            for (int j = 1; j < i; ++j) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            row.add(1);
            res.add(row);

        }
        return res;
    }
}

