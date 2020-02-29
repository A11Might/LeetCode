/**
 * @author qhhu
 * @date 2020/2/29 - 9:15
 *
 * [260] 只出现一次的数字 III
 *
 * 题目: 给定一个整数数组 nums, 其中恰好有两个元素只出现一次, 其余所有元素均出现两次. 找出只出现一次的那两个元素.
 *
 * 难度: medium
 *
 * 思路: a. 对所有数字求异或, 相同的数字会抵消, 最后剩下两个只出现 1 次的数字的异或和 xor.
 *      b. 找到 xor 中为 1 的位, 该位置 n 为两数不同的地方.
 *      c. 使用位置 n, 将数组分为 n 位置等于 0 和等于 1 的两组, 同时两个不一样的数字也分别在两组.
 *      d. 分别求两组所有数的异或和, 最终结果即为两个只出现一次的数.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        xor = (xor & (xor - 1)) ^ xor;
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((xor & num) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        return new int[] {num1, num2};
    }
}