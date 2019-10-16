import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/16 - 9:33
 *
 * [93] 复原ip地址
 *
 * 题目：复原并返回给定只包含数字的字符串所有可能的ip地址格式
 *
 * 难度：medium
 *
 * 思路：回溯，
 *      类比背包问题，当前遍历到的数字是否放入当前数值中
 */
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, 0, new int[4], 0, ans);

        return ans;
    }

    /**
     * dfs
     * @param s 给定字符串
     * @param index 当前遍历到字符串中的位置
     * @param ip 4位整型数组，对应当前收集ip中的4个数字
     * @param count 当前ip已经收集了几个数字
     * @param ans 所有可能ip
     */
    private void dfs(String s, int index, int[] ip, int count, List<String> ans) {
        if (index == s.length()) {
            if (count == 4) {
                ans.add(getString(ip));
            }
            return;
        }
        // ip中的每个数字至少有一位
        if (index == 0) {
            ip[count++] = s.charAt(index) - '0';
            dfs(s, index + 1, ip, count, ans);
        } else {
            // 不将当前值当做ip中的一个数字，继续扩大当前数值大小
            int cur = ip[count - 1] * 10 + ((s.charAt(index) - '0'));
            // Check if the current segment is valid :
            // 1. less or equal to 255
            // 2. the first character could be '0'
            // only if the segment is equal to '0'
            if (cur <= 255 && ip[count - 1] != 0) {
                ip[count - 1] = cur;
                dfs(s, index + 1, ip , count, ans);
                ip[count - 1] = cur / 10;
            }
            // 将当前值当做ip中的一个数字，开始收集下一个数字
            if (count < 4) {
                ip[count++] = s.charAt(index) - '0';
                dfs(s, index + 1, ip, count, ans);
                count--;
            }
        }
    }

    private String getString(int[] arr) {
        String str = arr[0] + "";
        for (int i = 1; i < 4; i++) {
            str += "." + arr[i];
        }

        return str;
    }
}

