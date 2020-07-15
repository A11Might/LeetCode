import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=118 lang=java
 *
 * [118] 杨辉三角
 *
 * 题目：给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 难度：easy
 * 
 * 思路：从上到下依次计算每一行，对于每一行，先把 1 放在首尾两个位置，然后计算中间的数。
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sublist = new ArrayList<>();
        List<Integer> prelist = new ArrayList<>();
        // 加入第一行
        sublist.add(1);
        ans.add(sublist);
        for (int i = 1; i < numRows; i++) {
            sublist = new ArrayList<>();
            sublist.add(1);
            for (int j = 0; j < i - 1; j++) {
                // 下一行等于上一行相邻两数之和
                sublist.add(prelist.get(j) + prelist.get(j + 1));
            }
            sublist.add(1);
            ans.add(sublist);
            prelist = sublist;
        }
        return ans;
    }
}