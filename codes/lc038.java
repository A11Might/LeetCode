/*
 * @lc app=leetcode.cn id=38 lang=java
 *
 * [38] 报数
 * 
 * 按照人肉读写
 */
class Solution {
    public String countAndSay(int n) {
        String str = "1";
        // 第n次报数
        for (int i = 2; i <= n; ++i) {
            // 进行一次报数
            StringBuilder sb = new StringBuilder();
            char pre = str.charAt(0);
            int times = 1;
            for (int j = 1; j < str.length(); ++j) {
                char cur = str.charAt(j);
                if (cur == pre) {
                    times++;
                } else {
                    sb.append(times).append(pre);
                    pre = cur;
                    times = 1;
                }
            }
            sb.append(times).append(pre);
            str = sb.toString();
        }
        return str;
    }
}

