import java.util.HashMap;

/**
 * @author qhhu
 * @date 2020/7/30 - 10:08
 *
 * [166] 分数到小数
 *
 * 题目：给定两个整数，分别代表分数的分子和分母，请以字符串形式返回小数，如果为循环小数，将循环部分括在括号内
 *
 * 难度：medium
 *
 * 思路：模拟，高精度除法，每次将余数乘 10 再除以除数，当同一个余数出现两次时，我们就找到了循环节。
 *                     所以我们可以用一个哈希表记录所有余数所对应的商的位数，当计算到相同的余数时，
 *                     上一次余数对应商的位数之后到当前位置之间的数，就是该小数的循环节
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public String fractionToDecimal(int numerator , int denominator) {
        long n = numerator, d = denominator;
        boolean minus = false;
        if (n * d < 0) minus = true;
        n = Math.abs(n);
        d = Math.abs(d);
        // 计算整数部分
        String ret = n / d + "";
        n %= d;
        if (n == 0) return minus ? "-" + ret : ret;
        ret += ".";
        // 计算小数部分
        // 哈希表记录所有余数所对应的商的位数
        // 当计算到相同的余数时，上一次余数对应商的位数之后到当前位置之间的数，就是该小数的循环节
        HashMap<Long, Integer> hash = new HashMap<>();
        while (n != 0) {
            if (hash.containsKey(n)) {
                ret = ret.substring(0, hash.get(n)) + "(" + ret.substring(hash.get(n)) + ")";
                break;
            } else {
                hash.put(n, ret.length());
            }
            n *= 10;
            ret += n / d;
            n %= d;
        }
        return minus ? "-" + ret : ret;
    }
}
