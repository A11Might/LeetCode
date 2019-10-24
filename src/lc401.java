import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/10/24 - 22:18
 *
 * [401] 二进制手表
 *
 * 题目: 二进制手表顶部有 4 个 LED 代表小时（1, 2, 4, 8），底部的 6 个 LED 代表分钟（1, 2, 4, 8, 16, 32）
 *       给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间
 *
 * 难度: easy
 *
 * 思路: 回溯,
 *       lc77 “n个数中选k个数” 的变形问题
 *      可以理解为从10个位置中取num个位置的组合问题
 *      int[] leds 的前四位表示小时，后六位表示分钟
 *      通过回溯选出所有的组合
 */
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new ArrayList<>();
        int[] leds = new int[10];
        dfs(num, 0, 0, leds, ans);

        return ans;
    }

    private void dfs(int num, int cnt, int index, int[] leds, List<String> ans) {
        if (cnt == num) {
            int hour = leds[0] * 8 + leds[1] * 4 + leds[2] * 2 + leds[3];
            int minu = leds[4] * 32 + leds[5] * 16 + leds[6] * 8 + leds[7] * 4 + leds[8] * 2 + leds[9];
            if (hour < 12 && minu < 60) {
                String s = String.format("%d:%02d", hour, minu);
                ans.add(s);
            }

            return;
        }
        // 剪枝
        // 当前还需好num - cnt个灯，所以[1, n]中至少要有num - cnt个元素
        // i最多为10 - (num - cnt) + 1(再大就没有足够的空位来找到num个灯的组合)
        for (int i = index; i < 10 - (num - cnt) + 1; i++) {
            leds[i] = 1;
            // i以前的数字已经考虑过了
            dfs(num, cnt + 1, i + 1, leds, ans);
            leds[i] = 0;
        }
    }
}
