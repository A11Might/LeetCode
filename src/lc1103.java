/**
 * @author qhhu
 * @date 2020/3/5 - 9:34
 *
 * [1103] 分糖果 II
 *
 * 题目：给定 candies 个糖果，分给 n = num_people 个小朋友。给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，以此类推，直到最后一个小朋友
 *      n 颗。然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗
 *      糖果。重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手
 *      中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 *      返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 *
 * 难度：easy
 *
 * 思路：模拟分糖的过程即可。
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] ret = new int[num_people];
        // index 表示当前分到第几个小朋友；curCandy 表示当前小朋友可以分得的糖果数。
        int index = 0, curCandy = 1;
        while (candies > 0) {
            // 取模找到当前轮到的小朋友的下标
            index %= num_people;
            if (candies < curCandy) {
                ret[index] += candies;
                break;
            }
            candies -= curCandy;
            ret[index++] += curCandy++;
        }

        return ret;
    }
}