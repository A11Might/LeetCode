import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/7/14 - 22:08
 *
 * [119] 杨辉三角 II
 *
 * 题目：杨辉三角 II
 *
 * 难度：easy
 *
 * 思路: 从上到下依次计算每一行，由于每行的值仅与上一行的值有关，所以可以用滚动数组优化。，对于每一行，先把 1 放在首尾两个位置，然后计算中间的数。
 */
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> sublist = new ArrayList<>();
        // 第 0 行
        sublist.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> pre = sublist;
            sublist = new ArrayList<>();
            sublist.add(1);
            for (int j = 0; j < i - 1; j++) {
                // 下一行等于上一行相邻两数之和
                sublist.add(pre.get(j) + pre.get(j + 1));
            }
            sublist.add(1);
        }
        return sublist;
    }
}
