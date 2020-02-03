import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 *
 * 题目: 给定包含数字2-9的字符串, 返回所有它能表示的字母的组合
 *      (2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz")
 *
 * 难度: medium
 * 
 * 思路: 回溯, 类似背包问题: 装与不装变成装a或b或c多种情况
 */
class Solution {
    /**
     * 时间复杂度: O(3 ^n * 4 ^m) (n为输入数字中对应3个字母的数目, m为输入数字中对应4个字母的数目)
     * 空间复杂度: O(3 ^n * 4 ^m)
     */
    private String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();
        List<String> ans = new ArrayList<>();
        dfs(digits, 0, ans, new StringBuilder());

        return ans;
    }

    private void dfs(String digits, int index, List<String> ans, StringBuilder curStr) {
        if (index == digits.length()) {
            ans.add(curStr.toString());
            return;
        }
        // 当前数字对应的字母集
        String letters = keys[digits.charAt(index) - '0'];
        // 相当于背包的装与不装, 这里是装a或b或c
        for (char chr : letters.toCharArray()) {
            curStr.append(chr);
            dfs(digits, index + 1, ans, curStr);
            curStr.deleteCharAt(index); // 回溯
        }
    }
}