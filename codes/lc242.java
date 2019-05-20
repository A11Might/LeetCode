/*
 * @lc app=leetcode.cn id=242 lang=java
 *
 * [242] 有效的字母异位词
 * 
 * 1、按asicII码排序后，比较两个字符串是否相等
 * 2、只包含小写字母，用数组注册一个字符串，再校验另一个字符串
 * 3、若包含Unicode字符，用哈希表注册
 */
class Solution {
    public boolean isAnagram1(String s, String t) {
        char[] chrsOfS = s.toCharArray();
        char[] chrsOfT = t.toCharArray();
        java.util.Arrays.sort(chrsOfS);
        java.util.Arrays.sort(chrsOfT);
        s = String.valueOf(chrsOfS);
        t = String.valueOf(chrsOfT);
        return s.equals(t);
    }

    public boolean isAnagram2(String s, String t) {
        int[] flag = new int[26];
        char[] chrsS = s.toCharArray();
        char[] chrsT = t.toCharArray();
        for (char chr : chrsS) {
            int index = chr - 'a';
            flag[index]++;
        }
        for (char chr : chrsT) {
            int index = chr - 'a';
            flag[index]--;
        }
        for (int times : flag) {
            if (times != 0) {
                return false;
            }
        }
        return true;
    }
}

