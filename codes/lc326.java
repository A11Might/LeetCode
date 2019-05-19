/*
 * @lc app=leetcode.cn id=326 lang=java
 *
 * [326] 3的幂
 * 
 * 1、暴力循环
 * 2、对于10进制数来说，10的n次幂表达为10，100，100 对于2进制数来说，2的n次幂的二进制表达为 10,100,100 3进制同理
 * 3、3的幂次的质因子只有3，而所给出的n如果也是3的幂次，故而题目中所给整数范围内最大的3的幂次的因子只能是3的幂次，1162261467是3的19次幂，是整数范围内最大的3的幂次
 * 4、在整数范围内判断n是不是3的某个幂
 */
class Solution {
    public boolean isPowerOfThree1(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 3) {
            return false;
        }
        while (n != 1) {
            if (n % 3 == 0) {
                n = n / 3;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPowerOfThree2(int n) {
        if (n <= 0) {
            return false;
        }
        String str = Integer.toString(n, 3); // 转为3进制
        return str.lastIndexOf("1") == 0 && str.indexOf("2") == -1; // 首位为1且其余位为0即为3的n次幂
    }

    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public boolean isPowerOfThree4(int n) {
        if (n == 0)
            return false;
        if (n == Math.pow(3, 0) | n == Math.pow(3, 2) | n == Math.pow(3, 1) | n == Math.pow(3, 2) | n == Math.pow(3, 3)
                | n == Math.pow(3, 4) | n == Math.pow(3, 5) | n == Math.pow(3, 6) | n == Math.pow(3, 7)
                | n == Math.pow(3, 8) | n == Math.pow(3, 9) | n == Math.pow(3, 10) | n == Math.pow(3, 11)
                | n == Math.pow(3, 12) | n == Math.pow(3, 13) | n == Math.pow(3, 14) | n == Math.pow(3, 15)
                | n == Math.pow(3, 16) | n == Math.pow(3, 17) | n == Math.pow(3, 18) | n == Math.pow(3, 19)
                | n == Math.pow(3, 20))
            return true;
        return false;
    }

}

