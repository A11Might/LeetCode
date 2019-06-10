import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 * 
 * 题意：给出一个区间的集合，请合并所有重叠的区间
 * 
 * 思路：按照区间的start大小排序后，合并的区定是连续的
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, new IntervalComparator()); // 按照区间的start大小排序后

        for (int[] interval : intervals) {
            // 若当前区间为第一个元素或与当前区间前一个区间不相交，则直接加入
            if (res.isEmpty() || res.get(res.size() - 1)[1] < interval[0]) {
                res.add(interval);
            // 当前区间前一个区间相交，则更新前一个区间
            } else {
                int newEnd = Math.max(res.get(res.size() - 1)[1], interval[1]);
                res.set(res.size() - 1, new int[] {res.get(res.size() - 1)[0], newEnd});
            }
        }

        return res.toArray(new int[0][]); // 要存储列表的元素的数组，如果它够大; 否则，为此目的分配相同运行时类型的新数组
    }

    // 按照区间的start大小排序后
    public class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
        }
    }
}

