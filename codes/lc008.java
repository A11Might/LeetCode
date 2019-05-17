/*
 * @lc app=leetcode.cn id=8 lang=java
 *
 * [8] 字符串转换整数 (atoi)
 * 
 * 按题意编写代码
 */
class Solution {
    public int myAtoi(String str) {
        // 实例 ""
        if (str == null || str.length() < 1) {
            return 0;
        }
        // 实例 "  "
        if (str.replace(" ", "").equals("")) {
            return 0;
        }
        str = str.trim(); // 去首位的空格
        boolean flag = false; // 默认返回值为正数
        long res = 0; // 返回值
        int i = 0;
        // 判断返回值是正数还是负数
        if (str.charAt(i) == '-') {
            flag = true;
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }
        // 将字符转化为整数
        for (; i < str.length(); ++i) {
            // 字符是数字则转化为正数，否则结束转化
            if (isNumber(str, i)) {
                res = res * 10 + (str.charAt(i) - '0');
            } else {
                break;
            }
            // 判断是否溢出
            if (res > Integer.MAX_VALUE) {
                if (flag) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }

        }
        // 返回转化后的结果
        if (flag) {
            return (int) (- res);
        } else {
            return (int) res;
        }
    }

    // 判断字符是否是数字
    private boolean isNumber(String str, int index) {
        return '0' <= str.charAt(index) && str.charAt(index) <= '9';
    }
}

