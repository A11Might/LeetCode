import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=13 lang=java
 *
 * [13] 罗马数字转整数
 * 
 * 使用HashMap存储字符大小，遍历字符串中字符
 * 若当前字符代表的值不小于其右边，就加上该值；否则就减去该值
 */
class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int n = s.length();
        int res = map.get(s.charAt(n - 1)); // 最后的元素是 +
        // 遍历前n - 1个元素
        for (int i = 0; i <= n - 2; ++i) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
                res += map.get(s.charAt(i));
            } else {
                res -= map.get(s.charAt(i));
            }
        }
        return res;
    }
}

