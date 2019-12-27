import java.util.Arrays;

/**
 * @author qhhu
 * @date 2019/12/27 - 8:54
 *
 * [455] 分发饼干
 *
 * 题目: 给若干个孩子发饼干, 第i个孩子的胃口值为g[i], 第j块饼干的大小为s[j], 当s[j] > g[i]把饼干j
 *      分配给孩子i, 这个孩子就能得到满足, 返回最多可以满足的孩子数
 *
 * 难度: easy
 *
 * 思路: 贪心, 用尽可能小的饼干去满足的孩子
 */
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0, cookie = 0;
        while (child < g.length && cookie < s.length ){ //当其中一个遍历就结束
            if (g[child] <= s[cookie]){ //当用当前饼干可以满足当前孩子的需求，可以满足的孩子数量+1
                child++;
            }
            cookie++; // 饼干只可以用一次，因为饼干如果小的话，就是无法满足被抛弃，满足的话就是被用了
        }
        return child;
    }
}
