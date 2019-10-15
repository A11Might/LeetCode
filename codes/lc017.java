import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 *
 * 题目：给定包含数字2-9的字符串，返回所有它能表示的字母的组合
 *
 * 难度：medium
 * 
 * 思路：回溯所有可能(相当于背包问题：装与不装变成a或b或c多种情况)
 */
class Solution {
    private String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.equals("")) {
            return ans;
        }
        char[] chrs = digits.toCharArray();
        dfs(chrs, 0, ans, "");

        return ans;
    }

    private void dfs(char[] chrs, int index, List<String> ans, String curStr) {
        if (index == chrs.length) {
            ans.add(curStr);
            return;
        }
        // 当前数字对应的字母集
        String letters = dict[chrs[index] - '0'];
        // 相当于背包的装与不装，这里是a或b或c
        for (int i = 0; i < letters.length(); i++) {
            dfs(chrs, index + 1, ans, curStr + letters.charAt(i));
        }
    }
}