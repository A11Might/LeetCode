/*
 * @lc app=leetcode.cn id=204 lang=java
 *
 * [204] 计数质数
 * 
 * 厄拉多塞筛法：比如说求20以内质数的个数,首先0,1不是质数.
 * 2是第一个质数,然后把20以内所有2的倍数划去.
 * 2后面紧跟的数即为下一个质数3,然后把3所有的倍数划去.
 * 3后面紧跟的数即为下一个质数5,再把5所有的倍数划去.
 * 以此类推.
 * 
 * 遍历索引从(2,int(n**0.5)+1)而非(2,n).
 * 这个技巧是可以验证的,比如说求9以内的质数个数,那么只要划掉sqrt(9)以内的质数倍数,剩下的即全为质数.
 * 所以在划去倍数的时候也是从i*i开始划掉,而不是i+i.
 */
class Solution {
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        int[] count = new int[n]; // 统计所有小于非负整数 n 的质数的数量
        for (int i = 0; i < n; ++i) {
            count[i] = 1;
        }
        count[0] = 0;
        count[1] = 0;
        for (int i = 2; i < Math.pow(n, 0.5) + 1; ++i) {
            if (count[i] == 1) {
                int start = i * i;
                while (start < n) {
                    count[start] = 0;
                    start += i;
                }
            }
        }
        int sum = 0;
        for (int num : count) {
            sum += num;
        }
        return sum;
    }
}

