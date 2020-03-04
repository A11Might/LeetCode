/**
 * @author qhhu
 * @date 2020/3/4 - 9:03
 *
 * [367] 有效的完全平方数
 *
 * 题目：判断给定正整数 num 是否是一个完全平方数。
 *
 * 难度：easy
 *
 * 思路：1. 二分查找
 *      2. 牛顿迭代法：a. 取 num / 2 作为初始近似值；
 *                   b. 当 x * x > num 时，用牛顿迭代法去计算下一个近似值：x = 1 / 2 * (x + num / x)；
 *                   c. 最后返回 x * x == num。
 *      3. 平方序列：1,4,9,16,..。间隔：3,5,7,...。间隔为等差数列，使用这个特性可以得到从 1 开始的平方序列。
 */
class Solution {
    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public boolean isPerfectSquare1(int num) {
        long lo = 1, hi = num / 2 + 1;
        while (lo <= hi) {
            long mid = lo + ((hi - lo) >> 1);
            long product = mid * mid;
            if (product < num) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return num == lo * lo;
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public boolean isPerfectSquare2(int num) {
        if (num == 0) return false;
        if (num == 1) return true;
        long ret = num / 2;
        while (ret * ret > num) {
            ret = (ret + num / ret) / 2;
        }

        return ret * ret == num;
    }

    /**
     * 时间复杂度：O()
     * 空间复杂度：O()
     */
    public boolean isPerfectSquare3(int num) {
        if (num == 0) return false;
        long ret = 0, interval = 1;
        while (ret < num) {
            ret += interval;
            interval += 2;
        }

        return ret == num;
    }
}