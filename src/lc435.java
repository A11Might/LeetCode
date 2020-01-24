import java.util.Arrays;

/**
 * @author qhhu
 * @date 2019/12/29 - 15:07
 *
 * [435] 无重叠区间
 *
 * 题目: 给定一个区间的集合, 返回需要移除区间的最小数量, 使剩余区间互不重叠
 *
 * 难度: medium
 *
 * 思路: 贪心, 按照结束位置先后选取能够选取的区间
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn) n为总区间个数
     * 空间复杂度: O(1)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]); // 按照区间结束位置先后排序
        int end = Integer.MIN_VALUE; // 上一个区间结束的位置
        int ans = 0;
        // 按照结束位置先后选取能够选取的区间, 如下
        // [[1,2], [2,3], [1,3], [3,4]]
        // 先选取[1, 2], 后选取[2, 3], 最后选取[3, 4]
        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                // 当前区间可以选取, 更新结束为止
                end = interval[1];
            } else {
                // 当前区间不可以选取, 更新移除区间数
                ans++;
            }
        }

        return ans;
    }
}