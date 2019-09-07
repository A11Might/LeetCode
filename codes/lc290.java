import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=290 lang=java
 *
 * [290] 单词规律
 * 
 * 题目：判断给定字符串str是否与遵循pattern的规律
 * 
 * 思路：查找表map
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        int pLen = pattern.length();
        int sLen = strs.length;
        if (pLen != sLen) {
            return false;
        }
        HashMap<Character, String> kv = new HashMap<>(); // <char, string>
        HashMap<String, Character> vk = new HashMap<>(); // <string, char>
        for (int i = 0; i < pLen; i++) {
            char curChr = pattern.charAt(i);
            // 当前字母未注册过
            if (!kv.containsKey(curChr)) {
                if (vk.containsKey(strs[i])) { // 实例："abba"\n"dog dog dog dog"
                    return false;
                }
                kv.put(curChr, strs[i]);
                vk.put(strs[i], curChr);
            // 当前字母注册过
            } else {
                if (!kv.get(curChr).equals(strs[i])) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

