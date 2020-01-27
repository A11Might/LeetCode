import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/1/27 - 10:16
 *
 * [763] 划分字母区间
 *
 * 题目: 给定一个仅由小写字母组成的字符串, 尽可能地将字符串划分为多个片段, 同一个字母只会出现在其中一个片段中, 返回表示每个字符串片段的长度列表
 *
 * 难度: medium
 *
 * 思路: 贪心算法, 从第一个字母开始分析, 假设第一个字母是'a', 那么第一个区间一定包含最后一次出现的'a'. 但第一个出现的'a'和
 *      最后一个出现的'a'之间可能还有其他字母, 对于遇到的每一个字母, 去找这个字母最后一次出现的位置, 用来更新当前的区间
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public List<Integer> partitionLabels(String S) {
        int len = S.length();
        if (len == 0) return Collections.emptyList();
        // 统计每个字母最后出现的位置
        int[] lastIndexOfChar = new int[26];
        for (int i = 0; i < len; i++) {
            // 总是记录当前字母出现的最后位置
            lastIndexOfChar[S.charAt(i) - 'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();
        int start = 0, end = 0; // 当前区间[start, end]
        for (int i = 0; i < len; i++) {
            // 使用当前字母最后一次出现的位置, 来更新当前的区间
            end = Math.max(end, lastIndexOfChar[S.charAt(i) - 'a']);
            // 当前区间中的字母都在区间中, 划分出了一个字符串片段
            if (i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }

        return ans;
    }
}
