/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 *
 * 题目：给定一个字符串 s，找到 s 中最长的回文子串
 *
 * 难度: medium
 *
 * 思路：1、Manacher算法
 *      2、中心扩展法，回文中心的两侧互为镜像，所以回文可以从它的中心展开，并且只有2n-1个这样的中心(一个元素为中心的情况有n个，两个元素为中心的情况有n-1个)
 *      3、动态规划，dp[i][j] == true, s.substring(i, j + 1)为回文子串
 *                  状态转移方程：dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1])
 */
class Solution {
    /**
     * Manacher算法
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2)
			return s;
		String chrs = manacherString(s); // 字符串预处理
		int[] pArr = new int[chrs.length()]; // 回文半径数组
		int C = -1; // 最早到达最右回文右边界时所对应的回文中心
        int R = -1; // 最右回文半径的右边界
        int longestPalindrome = 0;
        int start = 0;
        int end = 0;
		for (int i = 0; i != chrs.length(); i++) {
            // 两种情况：
            // a. i不在R内，需暴力扩，i位置的回文子串至少是它自己即1
            // b. i在R内，则i位置的回文长度至少为pArr[2 * C - i](i' 位置字符的回文在L和R之间)
            //    和R - i(i' 位置字符的回文在L和R之外)中较小值，即使i'位置字符的回文在L上也是在
            //    上述值上继续增加的，只需额外再向外扩一次即可判断具体是哪种情况
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1; // i' 位置为c - (i - c)即2 * c - i
            // 暴力扩时，pArr[i]初始值为1，依次向外扩增加值
            // 在之前加速的基础上，继续外扩
			while (i + pArr[i] < chrs.length() && i - pArr[i] > -1) {
				if (chrs.charAt(i + pArr[i]) == chrs.charAt(i - pArr[i])) {
					pArr[i]++;
				} else {
					break;
				}
			}
			// 实时更新R 和 C
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
            }
            // 当最长回文子串变化时，更新start和end
            if (longestPalindrome < pArr[i] - 1) {
                longestPalindrome = pArr[i] - 1;
                start = i - (pArr[i] - 1); // 字符串预处理过后，奇偶回文情况相同
                end = i + (pArr[i] - 1);
            }
        }

        return chrs.substring(start, end + 1).replace("#", "");
    }

    private static String manacherString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < str.length(); ++i) {
            sb.append(str.charAt(i));
            sb.append('#');
        }

        return sb.toString();
	}

    /**
     * 中心扩展法
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (s == null || len < 2) {
            return s;
        }
        int longestPalindrome = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < len; ++i) {
            int len1 = expendAroundCenter(s, i, i); // 22(i)22，以i为中心的回文子串长度
            int len2 = expendAroundCenter(s, i, i + 1); // 2(i2)2，// 以i和i + 1为中心的回文子串长度
            longestPalindrome = Math.max(longestPalindrome, Math.max(len1, len2));
            // 当最长回文子串变化时，更新start和end
            if (longestPalindrome > end - start + 1) {
                start = i - (longestPalindrome - 1) / 2; // 22(i)22, 2(i2)2，起始位置奇偶回文不同
                end = i + longestPalindrome / 2; // 结束位置奇偶回文相同
            }
        }

        return s.substring(start, end + 1);
    }

    // 以left和right为中心的最长回文子串
    private int expendAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
        }

        return right - left - 1; // 退出循环的条件为str.charAt(left) != str.charAt(right)，right - left + 1 -2去掉不构成回文子串的两个字符
    }

    /**
     * 动态规划
     * 时间复杂度: O(n * n)
     * 空间复杂度: O(n * n)
     */
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (s == null || len < 2) {
            return s;
        }
        int longestPalindrome = 0; // 最长回文子串的长度
        int start = 0; // 最长回文子串的起始位置
        int end = 0; // 最长回文子串的结束位置
        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                // dp[i][j] == true, s.substring(i, j + 1)为回文子串
                // dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1])
                // 当s.substring(l + 1, r)最多只有一个字符时，其本身就是回文子串不用再通过dp[l + 1][r - 1]判断
                // 另外: 若写成((left == right) || (s.charAt(left) == s.charAt(right) && dp[left + 1][right - 1]))
                //       当"cbbd", 若left和right为紧挨着的两个b时, 再去dp[left + 1][right - 1]会出错, 所以写成如下形式
                if ((s.charAt(left) == s.charAt(right)) && (right - left < 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    // 更新最长回文子串的长度和最长回文子串位置
                    if (right - left + 1 > longestPalindrome) {
                        longestPalindrome = right - left + 1;
                        start = left;
                        end = right;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }
}

