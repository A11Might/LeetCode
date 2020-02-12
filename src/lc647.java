/*
 * @lc app=leetcode.cn id=647 lang=java
 *
 * [647] 回文子串
 * 
 * 题目: 给定一个字符串, 计算这个字符串中有多少个回文子串.
 *      (开始或结束位置不同的子串, 即使有相同的字符组成, 也被记为不同的子串)
 *
 * 难度: medium
 *
 * 思路: 1. 动态规划, dp[i][j]表示substring[i, j]是否为回文子串
 * 			       状态转移方程, dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1])
 * 		2. 中心扩展法, 从每个可能的回文串中心位置开始, 尽可能扩大它的回文区间, 并同时统计回文串数量
 * 		3. Manacher算法, 每个位置上的最长回文半径和即为所有回文子串个数
 */
class Solution {
	/**
	 * 时间复杂度: O(n ^ 2)
	 * 空间复杂度: O(n ^ 2)
	 */
	public static int countSubstrings(String s) {
		int len = s.length();
		if (s == null || s.length() == 0) {
			return 0;
		}
		// dp[i][j]表示substring[i, j]是否为回文子串
		boolean[][] dp = new boolean[len][len];
		int cnt = 0;
		// 注意, 循环要按照如下方式写
		// 因为要求dp[i][j]需要知道dp[i + 1][j - 1]
		for (int r = 0; r < len; ++r) {
			for (int l = 0; l <= r; ++l) {
				// dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1])
				// (s.charAt(i)==s.charAt(j)时, 当元素个数为1,2,3个时一定为回文子串, 不用再通过dp[l + 1][r - 1]判断
				if ((s.charAt(l) == s.charAt(r)) && (r - l < 2 || dp[l + 1][r - 1])) {
					dp[l][r] = true;
					// 更新回文子串个数
					cnt++;
				}
			}
		}

		return cnt;
	}


	/**
	 * 时间复杂度: O(n ^ 2)
	 * 空间复杂度: O(1)
	 */
	private int cnt = 0;
	public int countSubstrings2(String s) {
		for (int i = 0; i < s.length(); ++i) {
			count(s, i, i); // 以i为中心的回文串个数(回文串长度为奇数)
			count(s, i, i + 1); // 以[i, i + 1]为中心的回文串个数(回文串长度长度为偶数)
		}

		return cnt;
	}

	// 统计以[left, right]为中心的回文串个数
	private void count(String s, int left, int right) {
		while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
			cnt++;
			left--;
			right++;
		}
	}

	/**
	 * Manacher算法
	 */
    public int countSubstrings3(String s) {
        if (s == null || s.length() == 0)
			return 0;
		char[] charArr = manacherString(s); // 字符串预处理
		int[] pArr = new int[charArr.length]; // 回文半径数组
		int C = -1; // 最早到达最右回文右边界时所对应的回文中心
		int R = -1; // 最右回文半径的右边界
		for (int i = 0; i != charArr.length; i++) {
            // 两种情况：
            // a. i不在R内，需暴力扩，i位置的回文子串至少是它自己即1
            // b. i在R内，则i位置的回文长度至少为pArr[2 * C - i](i' 位置字符的回文在L和R之间)
            //    和R - i(i' 位置字符的回文在L和R之外)中较小值，即使i'位置字符的回文在L上也是在
            //    上述值上继续增加的，只需额外再向外扩一次即可判断具体是哪种情况
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1; // i' 位置为c - (i - c)即2 * c - i
            // 暴力扩时，pArr[i]初始值为1，依次向外扩增加值
            // 在之前加速的基础上，继续外扩
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
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
		}
		// 每个位置上的最长回文半径和，即为所有回文子串个数
		int sum = 0;
		// 初始化后的字符串第一个和最后一个位置不计入sum，它们不是原字符串的回文子串
        for (int i = 1; i < pArr.length - 1; i++) {
			sum += pArr[i] / 2; // pArr[i] / 2为原字符串最长回文半径
        }

        return sum;
    }

    private static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1]; // 相当于在每个字符后面加一个'#',再在第一个字符前加一个'#'
		int index = 0; // 当前字符位置
		for (int i = 0; i < res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++]; // i为res数组中的位置，若为偶数则放置'#'，奇数则放置当前字符(
		}
		return res;
	}
}

