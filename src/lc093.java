import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/16 - 9:33
 *
 * [93] 复原ip地址
 *
 * 题目: 复原并返回给定只包含数字的字符串所有可能的ip地址格式
 *
 * 难度: medium
 *
 * 思路: 回溯, 类比背包问题, 当前遍历到的数字是否放入当前ip地址的part中(ip = part.part.part.part)
 */
class Solution {
    /**
     * 时间复杂度: O(1)
     * 空间复杂度: O(1)
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, new StringBuilder(), ans);

        return ans;
    }

    private void dfs(String s, int index, int cnt, StringBuilder curStr, List<String> ans) {
        // 当获取到4段ip地址或者遍历完给定字符串时
        if (cnt == 4 || index == s.length()) {
            if (cnt == 4 && index == s.length()) {
                ans.add(curStr.toString());
            }
            return;
        }
        // 从当前剩余字符串中截取1到3位数字, 作为ip地址的一一个part(ip = part.part.part.part)
        for (int i = index; i < s.length() && i <= index + 2; i++) {
            // Check if the current segment is valid :
            // 1. less or equal to 255
            // 2. the first character could be '0' only if the segment is equal to '0'
            // 若当前剩余字符串首字符为0, 只能截取'0'
            if (s.charAt(index) == '0' && i != index) break;
            String part = s.substring(index, i + 1);
            if (Integer.parseInt(part) <= 255) {
                if (curStr.length() != 0) part = "." + part;
                curStr.append(part);
                dfs(s, i + 1, cnt + 1, curStr, ans);
                curStr.delete(curStr.length() - part.length(), curStr.length()); // 回溯
            }
        }
    }
}

