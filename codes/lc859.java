/*
 * @lc app=leetcode.cn id=859 lang=java
 *
 * [859] 亲密字符串
 * 
 * a[i]和a[j]交换，为亲密字符串
 *      1> a[i] == a[j]
 *      2> a[i] == b[j] && a[j] == b[i] (a[i] != a[j])
 */
class Solution {
    public boolean buddyStrings(String A, String B) {
        // 两字符串长度不一样，不可能为亲密字符
        if (A.length() != B.length()) {
            return false;
        }
        // 两字符串相同
        if (A.equals(B)) {
            int[] times = new int[26];
            for (int i = 0; i < A.length(); ++i) {
                times[A.charAt(i) - 'a']++;
            }
            for (int time : times) {
                if (time > 1) {
                    return true;
                }
            }
            return false;
        // 两字符串不同
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1) {
                        first = i;
                    } else if (second == -1) {
                        second = i;
                    } else {
                        return false; // 超过两个字符不同，不是亲密字符
                    }
                }
            }
            return A.charAt(first) == B.charAt(second) &&
                   A.charAt(second) == B.charAt(first) ? 
                   true : false;
        }
    }
}

