import java.util.Arrays;

/**
 * @author qhhu
 * @date 2020/1/24 - 12:51
 *
 * [452] 用最少数量的箭引爆气球
 *
 * 题目: 气球在一个水平数轴上摆放, 可以重叠, 飞镖垂直投向坐标轴, 使得路径上的气球都被刺破. 求解最小的投飞镖次数使所有气球都被刺破
 *
 * 难度: medium
 *
 * 思路: 贪心算法, 根据气球的结束坐标进行排序, 依次遍历气球的结束坐标, 若下个气球开始坐标在当前气球的结束坐标前, 则我们可以用一支箭一起引爆;
 *              若下个气球的开始坐标在当前气球的结束坐标后, 则必须增加箭的数量. 并记录下个气球的结束坐标
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(1)
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // sort by end
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);
        int arrows = 1;
        int end = points[0][1];
        for (int[] point : points) {
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
            if (point[0] > end) {
                arrows++;
                end = point[1];
            }
        }

        return arrows;
    }
}
