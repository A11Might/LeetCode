import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/1/26 - 13:23
 *
 * [5320] 餐厅过滤器
 *
 * 题目: 给定餐厅信息restaurants, 其中restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei].
 *      (过滤器veganFriendly为true时, 只能返回veganFriendly为true的餐厅, 过滤器veganFriendly为false时, 可以返回任一餐厅;
 *      过滤后返回餐厅的id, 按照rating从高到低排序, 若rating相同, 按id从高到低排序;
 *      veganFriendly为true时取1, 为false时取0)
 *
 * 难度: medium
 *
 * 思路: 暴力
 */
class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ans = new ArrayList<>();
        Arrays.stream(restaurants)
                .filter(o -> (veganFriendly == 1 ? o[2] == 1 : true) && o[3] <= maxPrice && o[4] <= maxDistance)
                .sorted((o1, o2) -> o2[1] == o1[1] ? o2[0] - o1[0] : o2[1] - o1[1]).forEach(o -> ans.add(o[0]));

        return ans;
    }
}
