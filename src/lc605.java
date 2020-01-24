/**
 * @author qhhu
 * @date 2020/1/24 - 19:18
 *
 * [605] 种花问题
 *
 * 题目: flowerbed数组中0代表没种花, 1表示已经种下了花. 花朵之间至少需要一个单位的间隔, 返回能否能种下n朵花
 *
 * 难度: easy
 *
 * 思路: 贪心算法, 依次遍历数组, 花朵能种就种上, 根据最终能种的花朵数判断
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) continue;
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int succ = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && succ == 0) {
                // 花朵能种就种上
                flowerbed[i] = 1;
                n--;
            }
        }

        return n <= 0;
    }
}
