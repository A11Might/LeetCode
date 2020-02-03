import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/17 - 19:35
 *
 * [131] 分割回文串
 *
 * 题目: 将给定字符串s, 分割成若干子串, 使得每个子串都是回文串. 返回s所有可能的分割方案
 *
 * 难度: medium
 *
 * 思路: 回溯(减而治之)
 */
class Solution {
    /**
     * 时间复杂度: O()
     * 空间复杂度: O(n) (递归栈的深度)
     */
    public List<List<String>> partition(String s) {
        if (s.length() == 0) return Collections.emptyList();
        List<List<String>> ans = new ArrayList<>();
        dfs(s, 0, new ArrayList(), ans);

        return ans;
    }

    private void dfs(String s, int index, List<String> sublist, List<List<String>> ans) {
        if (index == s.length()) {
            ans.add(new ArrayList(sublist));
            return;
        }
        //从index开始, 分别截取长度为1, 2, 3...的子串来判断是否是回文串, 若是则用剩下的部分继续递归调用dfs(减而治之)
        for (int i = index; i < s.length(); i++) {
            String substr = s.substring(index, i + 1);
            if (isPalindrome(substr)) {
                sublist.add(substr);
                dfs(s, i + 1, sublist, ans);
                sublist.remove(sublist.size() - 1); // 回溯
            }
        }
    }

    // 判断当前字符串是否是回文串
    private boolean isPalindrome(String substr) {
        int lo = 0, hi = substr.length() - 1;
        while (lo < hi) {
            if (substr.charAt(lo++) != substr.charAt(hi--)) return false;
        }

        return true;
    }
}