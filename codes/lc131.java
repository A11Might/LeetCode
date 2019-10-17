import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/17 - 19:35
 *
 * [131] 分割回文串
 *
 * 题目：将给定字符串s，分割成若干子串，使得每个子串都是回文串。返回s所有可能的分割方案
 *
 * 难度：medium
 *
 * 思路：回溯
 */
class Solution {
    public List<List<String>> partition1(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(String s, int index, List<String> subList, List<List<String>> ans) {
        int n = s.length();
        //到字符串末尾了，将本次结果记录下来
        if (index == n) {
            ans.add(new ArrayList<>(subList));
            return;
        }
        //从index开始，分别截取长度为1, 2, 3...的子串来判断是否是回文串，若是则用剩下的部分继续dfs
        for (int i = 1; i <= n - index; i++) {
            String curStr = s.substring(index, index + i);
            if (isPalindrome(curStr)) {
                subList.add(curStr);
                dfs(s, index + i, subList, ans);
                subList.remove(subList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }

        return true;
    }

    // my solution
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(s, 0, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(String s, int begin, int end, List<String> subList, List<List<String>> ans) {
        if (end == s.length()) {
            String cur = s.substring(begin, end);
            // 需要额外判断，最后一个子串是否是回文串(由case2产生)
            if (!cur.equals("") && isPalindrome(cur)) {
                subList.add(cur);
                ans.add(new ArrayList<>(subList));
                subList.remove(subList.size() - 1);
            }
            return;
        }
        // 将当前子串作为回文串
        String cur = s.substring(begin, end + 1);
        if (isPalindrome(cur)) {
            subList.add(cur);
            dfs(s, end + 1, end + 1, subList, ans);
            subList.remove(subList.size() - 1);
        }
        // 不将当前子串当做回文串，继续扩大子串大小
        dfs(s, begin, end + 1, subList, ans);
    }
}
