import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 * 
 * 回溯所有可能(相当于背包问题：装与不装变成a或b或c多种情况)
 */
class Solution {
    private HashMap<Character, String> map = new HashMap<>();
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        if (digits.length() != 0) {
            process("", digits, 0);
        }
        return res;
    }

    private void process(String listMember, String digits, int index) { // 因为需要每个回溯结果，所以返回值为void，在函数内收集结果。回溯的每个结果放在形参上，便于收集
        if (index == digits.length()) {
            res.add(listMember); // <-----收集回溯结果
        } else {
            int n = map.get(digits.charAt(index)).length(); // 当前数字对应字母数量
            // 相当于背包的装与不装，这里是a或b或c
            for (int i = 0; i < n; ++i) {
                // 将形参变化放在，递归调用内部(避免修改当前形参的值，影响后续递归调用)
                process(listMember + map.get(digits.charAt(index)).charAt(i), digits, index + 1);
            }
        }
    }
}

