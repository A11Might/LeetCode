import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 * 
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
 * 1、暴力算法
 * 2、滑动窗口，使用 HashSet 将字符存储在当前窗口[i, j)（最初 j = i）中。 然后我们向右侧滑动索引j，如果它不在 HashSet中，我们会继续滑动j
 *             直到 s[j] 已经存在于 HashSet 中，当我们找到重复的字符时，我们可以立即跳过该窗口
 *      1>使用HashMap注册
 *      2>使用asicII数组注册
 */
class Solution {
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> chrIndexMap = new HashMap<>(); // 注册已经遍历过的字符
        int res = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) { // 开一个[i, j]的窗口(i不会大于j)，计算最长子串
            if (chrIndexMap.containsKey(s.charAt(j))) {
                i = Math.max(chrIndexMap.get(s.charAt(j)) + 1, i); // 当前字符已经注册过，修正子串起始位置i，重新计算最长子串
            }
            res = Math.max(res, j - i + 1); // 实时更新无重复字符子串的最大长度
            chrIndexMap.put(s.charAt(j), j); // 注册当前字符
        }
        return res;
    }

    public int lengthOfLongestSubstring3(String s) {
        int[] chrIndex = new int[128]; // 用一个整数数组作为直接访问表来替换map
        int res = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            i = Math.max(chrIndex[s.charAt(j)], i); // 数组初始化为0，且前往后遍历，当出现重复元素时，chrIndex[s.charAt(j)]会变大，选出的最大值即为，修正后的起始位置
            res = Math.max(res, j - i + 1); // 实时更新无重复字符子串的最大长度
            chrIndex[s.charAt(j)] = j + 1; // 注册当前字符为j + 1，重置起始位置i时，直接取出使用即可
        }
        return res;
    }
}

