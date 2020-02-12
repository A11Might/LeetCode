import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=242 lang=java
 *
 * [242] 有效的字母异位词
 *
 * 题目: 判断给定字符串t是否是给定字符串s的字母异位词.
 *
 * 难度: easy
 *
 * 思路: 1. 按asicII码排序后, 比较两个字符串是否相等
 *      2. 因为只包含小写字母, 所以使用长度为26的整型数组对字符串出现的字符进行统计, 然后比较两个字符串出现的字符数量是否相同
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(n + m)
     */
    public boolean isAnagram(String s, String t) {
        char[] chrsOfS = s.toCharArray();
        char[] chrsOfT = t.toCharArray();
        Arrays.sort(chrsOfS);
        Arrays.sort(chrsOfT);
        return new String(chrsOfS).equals(new String(chrsOfT));
    }

    /**
     * 时间复杂度: O(n + m)
     * 空间复杂度: O(1)
     */
    public boolean isAnagram2(String s, String t) {
        int[] cnts = new int[26];
        for (char chr : t.toCharArray()) {
            cnts[chr - 'a']++;
        }
        for (char chr : s.toCharArray()) {
            cnts[chr - 'a']--;
        }        
        
        for (int cnt : cnts) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }
}

