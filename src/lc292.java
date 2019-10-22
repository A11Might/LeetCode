/*
 * @lc app=leetcode.cn id=292 lang=java
 *
 * [292] Nim 游戏
 * 
 * 题意：一堆石头，每次你们轮流拿掉1 - 3块石头，拿掉最后一块石头的人就是获胜者，你作为先手
 * 
 * https://leetcode-cn.com/problems/nim-game/solution/nimyou-xi-by-leetcode/
 * 思路：如果堆中石头的数量n不能被4整除，那么你总是可以赢得Nim游戏的胜利
 */
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}

