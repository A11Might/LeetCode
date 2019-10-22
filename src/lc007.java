/*
 * @lc app=leetcode.cn id=7 lang=java
 *
 * [7] 整数反转
 * 
 * 重复弹出x的最后一位数字，并将它推入rev的后面
 * 用数学的方法pop = x % 10; x /= 10;
 * temp = rev * 10 + pop; rev = temp;
 */
class Solution {
    public int reverse(int x) {
        /**
         * rev保存旧的翻转中间值, temp保存新的翻转过程中间值
         * 依次提取x的末位加入temp, 如果发生溢出则通过temp/10无法得到上一轮的翻转结果rev
         **/
        int rev = 0;
        while (x != 0) {
            int temp = rev * 10 + x % 10;
            if (temp / 10 != rev) {
                return 0;
            }
            rev = temp;
            x /= 10;
        }
        return rev;
    }
}

