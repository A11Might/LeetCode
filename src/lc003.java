import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 * 
 * 题目：给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
 * 
 * 思路：滑动窗口，使用 HashSet 将字符存储在当前窗口[i, j)（最初 j = i）中。 然后我们向右侧滑动索引j，如果它不在 HashSet中，我们会继续滑动j
 *             直到 s[j] 已经存在于 HashSet 中，当我们找到重复的字符时，我们可以立即跳过该窗口
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] chrIndex = new int[128];
        Arrays.fill(chrIndex, -1);
        int n = s.length();
        int l = 0, r = -1;
        int res = 0;
        while (r + 1 < n) {
            r++; // 不断扩大窗口
            if (chrIndex[s.charAt(r)] != -1) { // "abba"
                // 更新窗口时，只更新了窗口的左右边界，更新后窗口外之前窗口的值未变化(即窗口外的值仍有注册)
                // 所以当前元素在chrIndex中有出现时，需要判断想要更新的成为的左边界是否在窗口内，来决定是否更新窗口
                //          a、在窗口外说明已过期，无须更新
                //          b、在窗口内则任有效，更新左边界
                l = Math.max(l, chrIndex[s.charAt(r)] + 1);
            }
            res = Math.max(res, r - l + 1);
            chrIndex[s.charAt(r)] = r;
        }
        
        return res;
    }
}

